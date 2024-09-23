import json
import random
import boto3

sqs = boto3.resource('sqs')
queue = sqs.get_queue_by_name(QueueName='transaction_result_queue.fifo')

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
                response =queue.send_message(MessageBody=json.dumps({'message': message , 'tranasction_id': txn_id}))
                messageID = response.get('MessageId')
                return {
                    'statusCode': 200,
                    'body': json.dumps({
                        'message': message,
                        'transaction_id': txn_id,
                        'messageID': messageID
                    })
                }
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