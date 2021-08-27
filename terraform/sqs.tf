resource "aws_sqs_queue" "poc_ses_fifo" {
  name                        = "poc-ses.fifo"
  fifo_queue                  = true
  content_based_deduplication = true
}