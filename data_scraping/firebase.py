import json
import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore

# Use a service account
cred = credentials.Certificate('firebase.json')
firebase_admin.initialize_app(cred)

db = firestore.client()

# do the dishes
dishes = db.collection('dishes')

with open('.\clean_data\dishes.json', encoding='utf-8') as f:
	jsn = json.load(f)

for entry in jsn:
	try:
		dishes.document(entry['dish']).set(entry)
	except:
		print(entry['dish'])

# restaurants
res = db.collection('restaurants')

with open('.\clean_data\chinese.json', encoding='utf-8') as f:
	jsn = json.load(f)

for entry in jsn:
	try:
		res.document(entry['name']).set(entry)
	except:
		print(entry['name'])

with open('.\clean_data\desserts.json', encoding='utf-8') as f:
	jsn = json.load(f)

for entry in jsn:
	try:
		res.document(entry['name']).set(entry)
	except:
		print(entry['name'])

with open('.\clean_data\\fusion.json', encoding='utf-8') as f:
	jsn = json.load(f)

for entry in jsn:
	try:
		res.document(entry['name']).set(entry)
	except:
		print(entry['name'])

with open('.\clean_data\indian.json', encoding='utf-8') as f:
	jsn = json.load(f)

for entry in jsn:
	try:
		res.document(entry['name']).set(entry)
	except:
		print(entry['name'])

with open('.\clean_data\malay.json', encoding='utf-8') as f:
	jsn = json.load(f)

for entry in jsn:
	try:
		res.document(entry['name']).set(entry)
	except:
		print(entry['name'])

with open('.\clean_data\seafood.json', encoding='utf-8') as f:
	jsn = json.load(f)

for entry in jsn:
	try:
		res.document(entry['name']).set(entry)
	except:
		print(entry['name'])