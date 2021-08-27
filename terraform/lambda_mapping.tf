resource "aws_lambda_event_source_mapping" "event_source_mapping" {
  event_source_arn = aws_sqs_queue.poc_ses_fifo.arn
  function_name    = aws_lambda_function.poc_send_email_ses_lambda.arn
}