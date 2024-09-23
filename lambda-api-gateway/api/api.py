import json
import random

def lambda_handler(event, context):
    txn_id = "none"

    if event.get('ACC_NUMBER'):
    
        try:
            if event.get('ACC_NUMBER'):
                txn_id = random.getrandbits(128).to_bytes(16, 'little').hex()
                message = "Tranasction  processed successfully!!!"
        except (KeyError, json.JSONDecodeError):
            txn_id = "ERROR"

    return {
        'statusCode': 200,
        'body': json.dumps({
            'message': message,
            'transaction_id': txn_id
        })
    }