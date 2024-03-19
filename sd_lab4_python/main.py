from flask import Flask, jsonify, render_template, request, session, redirect, url_for
import requests
import json
import hashlib

app = Flask(__name__)

app.secret_key = 'javascript=))'
app.config['SESSION_PERMANENT'] = False

serverURL = 'https://localhost:8080'


# apel catre "https://localhost:8080/cheltuieli"
# raspunsul primit trimis ca JSON
@app.route('/', methods=['GET'])
def mainpage():
    if not is_logged_in():
        return redirect(url_for('login_form'))
    
    url = serverURL + '/cheltuieli'
    raspuns = requests.get(url, verify=False)
    
    listaCheltuieli = raspuns.json()

    total_sum = sum(cheltuiala['suma'] for cheltuiala in listaCheltuieli)

    
    return render_template("index.html", listaCheltuieli=listaCheltuieli, total=total_sum)


@app.route('/register', methods=['GET'])
def register_form():
    return render_template("register.html")


@app.route('/register', methods=['POST'])
def handle_register():
    firstName = request.form['firstName']
    lastName = request.form['lastName']
    username = request.form['username']
    password = request.form['password']

    hashedPassword = encode_string(username+password)
    
    registerRequest = {
        'id': -1,
        'firstName': firstName,
        'lastName': lastName,
        'username': username,
        'password': hashedPassword
    }
    
    session['username'] = username
    
    url = serverURL + '/register'
    response = requests.post(url, json=registerRequest, verify=False)
    
    if(response.status_code == 201):
        return redirect(url_for('mainpage'))
    else:
        error_message = "Inregistrarea a esuat. Numele de utilizator exista deja."
        return render_template('register.html', error_message=error_message)

@app.route('/add', methods=['GET'])
def add_cheltuiala_form():
    return render_template("adaugacheltuiala.html")

@app.route('/add', methods=['POST'])
def add_cheltuiala():
    suma = request.form['suma']
    descriere = request.form['descriere']
    
    print(suma + " " + descriere)
    
    url = serverURL + "/addcheltuiala"
    
    cheltuiala = {
        'id': -1,
        'userID': session['userID'],
        'suma': suma,
        'descriere': descriere 
    }
    
    response = requests.post(url, json=cheltuiala, verify=False)
            
    if(response.status_code == 201):
        return redirect(url_for('mainpage'))
    else:
        error_message = "Nu am putut adauga."
        return render_template('adaugacheltuiala.html', error_message=error_message)
    
    return redirect(url_for("mainpage"))

@app.route('/update/<int:id>', methods = ['GET'])
def update_form(id):
    #url = serverURL + "/getcheltuiala/" + str(id)    
    #response = requests.get(url, verify=False)
    #print(response.text)
    date = {
        'id': id
    }
    
    return render_template("updatecheltuiala.html", date=date)


@app.route('/updateCh', methods=['POST'])
def handle_update():
    id = request.form["id"]
    cheltuiala = {
        'id': int(id),
        'userID': int(session['userID']),
        'suma': float(request.form["suma"]),
        'descriere': str(request.form["descriere"])
    }
    
    print(cheltuiala)
    
    url = serverURL + "/updatecheltuiala/" + str(id)
    response = requests.put(url, json=cheltuiala, verify=False)
    
    
    return redirect(url_for("mainpage"))


@app.route('/delete/<int:id>', methods=['POST'])
def delete_cheltuiala(id):
    url = serverURL + "/deletecheltuiala/" + str(id)
    response = requests.delete(url, verify=False)
    return redirect(url_for('mainpage'))
    
@app.route('/logout')
def logout():
    session.pop('username', None)
    session.pop('userID', None)
    return render_template("login.html")


@app.route('/login', methods=['GET'])
def login_form():
    return render_template("login.html")


@app.route('/login', methods=['POST'])
def handle_login():
    username = request.form['username']
    password = request.form['password']
    
    hashedPassword = encode_string(username+password)
    
    loginRequest = {
        'username': username,
        'password': hashedPassword
    }
    
    url = serverURL + '/login'
    response = requests.post(url, json=loginRequest, verify=False)
    
    data = json.loads(response.text)
    
    if response.status_code == 200:
        session['userID'] = data['id']
        session['username'] = data['username']
        return redirect(url_for('mainpage'))
    else:
        error_message = "Eroare la username sau parola."
        return render_template("login.html", error_message=error_message)


def encode_string(string: str):
    hash = hashlib.sha256()
    hash.update(string.encode())
    encoded_pass = hash.hexdigest()
    return encoded_pass


def is_logged_in():
    return 'username' in session 

if __name__ == "__main__":
    app.run(debug=True)
