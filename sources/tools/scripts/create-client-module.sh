ROOT_DIR=$(git rev-parse --show-toplevel)
GRADLE_NAME="$1"

COMPOSE_FLAG="--useCompose"
if [ -z "$2"]; then
  COMPOSE_FLAG=""
fi

python3 $ROOT_DIR/tools/scripts/create-module.py \
  --proj-path=$ROOT_DIR \
  --gradle-name=$GRADLE_NAME \
  $COMPOSE_FLAG