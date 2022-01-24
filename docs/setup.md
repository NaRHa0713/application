# セットアップマニュアル

## 1.リポジトリのクローン
･以下のコマンドをサーバで実行して、リポジトリをクローンする。<br>
`git clone https://github.com/NaRHa0713/application.git`<br>

## 2.80番ポートの利用制限解除
･以下のコマンドを実行して80番ポートの利用をできるようにする。<br>
`sudo setcap CAP_NET_BIND_SERVICE+ep /usr/lib/jvm/java-11-amazon-corretto/bin/java`<br>

## 3.ディレクトリの移動
･以下のコマンドでプロジェクトがあるディレクトリに移動する。<br>
`cd application`<br>

## 4.アプリケーションの実行
･gradleをbashを利用して実行するために、以下のコマンドを実行する。<br>
`bash ./gradlew`<br>
･プロジェクト実行のために、以下のコマンドを実行する。<br>
`bash ./gradlew bootrun`<br>
･実行を終了するときはキーボードのCtrlとCを同時に押す。<br>
