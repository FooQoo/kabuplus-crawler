#!/bin/bash

IS_THERE_NECESSARY_OPT=false

while getopts d:o: OPT; do
  case $OPT in
  d)
    IS_THERE_NECESSARY_OPT=true
    dir=$OPTARG
    ;;
  o)
    IS_THERE_NECESSARY_OPT=true
    output=$OPTARG
    ;;
  : | \?)
    echo "Invalid args."
    ;;
  esac
done

if [ "${IS_THERE_NECESSARY_OPT}" != true ]; then
  echo "Not found necessary options."
  exit 1
fi

jsonname=`basename ${output} | awk -F '.' '{print $1}'`

# ファイル名取得
mapfile -t files < <(ls -1 "$dir" | grep -e "202006" -e "202007" -e "202008")

# header取得 エンコード実行
head -n 1 "$dir${files[0]}" | nkf | grep -v "^$" >"${output}"

# ファイル内容の集約
for filename in "${files[@]}"; do
  tail -n +2 "$dir$filename" | grep -v "^$" >>"${output}.bk"
done

nkf "${output}.bk" | grep -e "サービス" -e "情報通信" -e "倉庫・運輸" -e "その他製品" -e "医薬品" >>"$output"
rm "${output}.bk"

c=`cat <<EOF
import pandas as pd
import json
df = pd.read_csv("${output}", low_memory=False)
stocks = df.values.astype(str).tolist()
with open("${jsonname}.json", "w") as f:
  json.dump({'japan-all-stock-prices-2': stocks}, f, indent=2, ensure_ascii=False)
EOF`

python -c "$c"

rm "${output}"
