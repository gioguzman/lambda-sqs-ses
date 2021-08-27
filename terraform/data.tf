data "aws_iam_policy_document" "ses_access_template_policy" {
  statement {
    actions = [
      "ses:SendTemplatedEmail"
    ]
    resources = ["*"]
  }
}