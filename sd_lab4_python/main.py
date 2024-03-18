from flask import Flask, jsonify, render_template, request
import requests
import json

app = Flask(__name__)


@app.route('/')
def main():
    return render_template("index.html")

@app.route('/login', methods=['GET'])
def login_page():
    return render_template("login.html")

@app.route('/login', methods=['POST'])
def login():
    username = request.form["username"]
    password = request.form["password"]

    data = {
        'username': username,
        'password': password
    }

    url = 'https://localhost:8080/login'
    
    response = requests.post(url, json=data, verify=False)

    if(response.status_code == 200):
        return "succes"
    else:
        return "fail"


@app.route('/encrypt')
def encrypt():
    url = 'https://localhost:8080/test'

    response = requests.post(url, json="test", verify=False)
    print(response.json())
    return render_template("index.html")
#    return json.dumps(response.json())


@app.route('/addCheltuiala')
def addCheltuiala():
    suma = request.args.get('suma')
    descriere = request.args.get('descriere')

    cheltuiala = {
        'id': 0,
        'userID': 0,
        'suma': float(suma),
        'descriere': str(descriere)
    }

#    json_data = json.dumps(cheltuiala)

    url = 'https://localhost:8080/addcheltuiala'

    requests.post(url, json=cheltuiala, verify=False)

    print(suma + descriere)
    return render_template("index.html")

@app.route('/cheltuieli')
def getCheltuieli():
    url = 'https://localhost:8080/cheltuieli'

    response = requests.get(url, verify=False)

    return render_template("cheltuieli.html", cheltuieli=response.json())

@app.route('/createAccount', methods=['POST'])
def createAccount():
    firstName = request.form['firstName']
    lastName = request.form['lastName']
    username = request.form['username']

    # va fi modificat pe viitor
    password = request.form['password']

    user = {
        'id': 0,
        'firstName': firstName,
        'lastName': lastName,
        'username': username,
        'password': password
    }

    url = 'https://localhost:8080/createaccount'

    requests.post(url, json=user, verify=False)
        
    return render_template("index.html")


@app.route('/register')
def registerPage():
    return render_template("register.html")

if __name__ == "__main__":
    app.run(debug=True)
