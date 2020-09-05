```bash
# アカウント認証系
$ gcloud auth activate-service-account [SERVICE ACCOUNT NAME] --key-file=[KEY FILE]
$ gcloud auth list
$ gcloud config set account [ACCOUNT]
$ gcloud config set project [PROJECT ID]

# topic 設定
$ gcloud pubsub topics create stock
# deploy
$ gcloud functions deploy stock-crawler \
  --project stock-crawler-kabu-plus \
  --quiet \
  --entry-point=org.springframework.cloud.function.adapter.gcp.GcfJarLauncher \
  --runtime=java11 \
  --trigger-topic=stock \
  --source=target/ \
  --memory=512MB \
  --env-vars-file=env.yaml \
  --vpc-connector=function2sql \
  --timeout=300

$ gcloud scheduler jobs create pubsub stock \
   --topic stock \
   --message-body='fetch info' \
   --schedule '00 17 * * *' \
   --time-zone='Asia/Tokyo'

```