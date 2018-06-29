#!/bin/bash
PROTOC_PATH="$(which protoc)"
PROTOC_SRC_DIR="src/main/proto"
OUTPUT_DIR="src/main/java/com/me/harris/gen/"
IMPORT_PATH="src/main/proto/"
${PROTOC_PATH} --version
ls -al ${PROTOC_SRC_DIR}

DESTINATION_PATH="src/main/java/"

if [ ! -d "$OUTPUT_DIR" ]; then ## destination folder does not exists
mkdir "$OUTPUT_DIR"
else
echo "============= ${OUTPUT_DIR} already exist================="
rm -rf $OUTPUT_DIR
echo "=============creating a new ${OUTPUT_DIR} folder ============="
mkdir "$OUTPUT_DIR"
fi

## require javalite plugin found on your path
## ${PROTOC_PATH} --proto_path=${IMPORT_PATH} --javalite_out=${DESTINATION_PATH} ${PROTOC_SRC_DIR}/*

## tested on mac
${PROTOC_PATH} --proto_path=${IMPORT_PATH} --java_out=${DESTINATION_PATH} ${PROTOC_SRC_DIR}/*

## protoc --javalite_out=${OUTPUT_DIR} path/to/your/proto/file