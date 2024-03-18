from flask import Flask, render_template, request
import requests
import json

app = Flask(__name__)


@app.route('/')
def main():
    return render_template("index.html")

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

    url = 'https://localhost:8080/register'

    requests.post(url, json=user, verify=False)
        
    return render_template("index.html")


@app.route('/register')
def registerPage():
    return render_template("register.html")

if __name__ == "__main__":
    app.run(debug=True)
