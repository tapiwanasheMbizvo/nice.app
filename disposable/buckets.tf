resource "aws_s3_bucket" "deployment_artifacts_bkt" {

bucket = "deployart_020923_spring"

tags = {
  Name =  "Buckets for Deployment Artifacts"
}

}