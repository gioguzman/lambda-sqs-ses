resource "aws_iam_role" "poc_send_email_ses_lambda_role" {
  name               = "poc_send_email_ses_lambda_role"
  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "lambda.amazonaws.com"
      },
      "Effect": "Allow",
      "Sid": ""
    }
  ]
}
EOF
}

resource "aws_iam_policy" "poc_ses_template_policy" {
  name   = "poc_ses_template_policy"
  policy = data.aws_iam_policy_document.ses_access_template_policy.json
}

resource "aws_iam_role_policy_attachment" "poc_sqs_lambda_policy" {
  role       = aws_iam_role.poc_send_email_ses_lambda_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaSQSQueueExecutionRole"
}

resource "aws_iam_role_policy_attachment" "poc_lambda_to_ses" {
  role       = aws_iam_role.poc_send_email_ses_lambda_role.name
  policy_arn = aws_iam_policy.poc_ses_template_policy.arn
}