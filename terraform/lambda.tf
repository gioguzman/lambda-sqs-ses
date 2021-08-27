resource "aws_lambda_function" "poc_send_email_ses_lambda" {
  filename         = "../function/target/sendemail-0.0.1-SNAPSHOT-aws.jar"
  function_name    = "poc-send-email-ses-lambda"
  role             = aws_iam_role.poc_send_email_ses_lambda_role.arn
  handler          = "org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest"
  runtime          = "java11"
  memory_size      = "512"
  timeout          = "30"
  source_code_hash = filebase64sha256("../function/target/sendemail-0.0.1-SNAPSHOT-aws.jar")

  environment {
    variables = {
      EMAILRECIPENT = "email1@example.com"
      EMAILSENDER   = "email2@example.com"
      FUNCTION_NAME = "FunctionApplication"
      MAIN_CLASS    = "com.example.ses.function.sendemail.FunctionApplication"
    }
  }
}