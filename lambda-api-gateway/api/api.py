import json
import random
import boto3
import os

sqs_client = boto3.client("sqs", region_name="af-south-1")

def lambda_handler(event, context):
    txn_id = "none"
    message = "failed"
    routeKey = event.get('routeKey', '').split()[0]
    
    if routeKey == 'POST':
        try:
            body = event['body']
            body_dict = json.loads(body)
            acc_number = body_dict.get('ACC_NUMBER')
            
            if acc_number:
                txn_id = random.getrandbits(128).to_bytes(16, 'little').hex()
    
                # Do some DB stuff and return the message 
                queue_url = os.environ.get('QUEUE_URL')
                message = "Transaction processed successfully!"
                
                response = sqs_client.send_message(
                    QueueUrl=queue_url,
                    MessageBody=json.dumps({'message': message, 'transaction_id': txn_id}),
                    MessageGroupId='5858'+txn_id, 
                    MessageDeduplicationId='5854545'+txn_id 
                )
                
                messageID = response.get('MessageId')
                return {
                    'statusCode': 200,
                    'body': json.dumps({
                        'message': message,
                        'transaction_id': txn_id,
                        'messageID': messageID
                    })
                }
            else:
                message = "Account number not provided."
                
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