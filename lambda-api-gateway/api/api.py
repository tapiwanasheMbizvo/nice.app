import json
import random

def lambda_handler(event, context):
    txn_id = "none"
    message = "failed"
    routeKey = event.get('routeKey').split()[0]
    
    if routeKey =='POST':
        try:
            body = event['body']
            body_dict = json.loads(body)
            acc_number = body_dict.get('ACC_NUMBER')
            
            if acc_number:
                txn_id = random.getrandbits(128).to_bytes(16, 'little').hex()
    
                # do some db stuff , and return the message 
                message = "Transaction processed successfully!!!"
        except (KeyError, json.JSONDecodeError):
            txn_id = "ERROR"
            message = "Invalid input or JSON format."
    elif routeKey == 'GET':
        message = "Get Method not defined"
    return {
        'statusCode': 200,
        'body': json.dumps({
            'message': message,
            'transaction_id': txn_id
        })
    }