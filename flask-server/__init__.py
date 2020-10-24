import pymongo
from pymongo import MongoClient
from bson.objectid import ObjectId
from bson.json_util import loads, dumps

from flask import Flask
from flask_restful import Resource, Api, reqparse

from constants import CONN_STRING

client = MongoClient(CONN_STRING)
collection = client.data.data

app = Flask(__name__)
api = Api(app)

dish_parser = reqparse.RequestParser()
dish_parser.add_argument('id', type=str)
dish_parser.add_argument('name', type=str)


# DISH_STRUCTURE = {"name": "lmao", "review_score": 99}
class Dish(Resource):
    def get(self):
        # get by ID or name, if both are not present then return all dishes
        args = dish_parser.parse_args()

        if args['id'] != None:
            data = collection.find_one({"_id": ObjectId(args['id'])})
            data['_id'] = str(data['_id'])
            return data, 201
        elif args['name'] != None:
            data = collection.find_one({"name": args['name']})
            print(data, args['name'])
            data['_id'] = str(data['_id'])
            return data, 201
        else:
            data = collection.find({})
            ret_lst = []
            for x in data:
                x['_id'] = str(x['_id'])
                ret_lst.append(x)

            return ret_lst, 201


api.add_resource(Dish, '/dish')

if __name__ == "__main__":
    app.run(debug=True)
