# プロジェクト構成

## 概要
auto_follow_back<br>
└afb_web<br>
└afb_batch<br>
└afb_domain<br>
└afb_infra<br>

## auto_follow_back
Gradle親プロジェクト。実体はなく、プロジェクト全体で使用するプラグインやSping/Kotlinのバージョンを定義している。<br>

## afb_web
Spring Bootを使用したWebアプリケーション。<br>
プレゼンテーション層（WebブラウザとのIO）とアプリケーション特有のUseCase（認証やセッション）を管理する。<br>

## afb_batch
Spring Bootを使用したBatchプログラム。<br>
バッチ処理に必要なデータの管理や、ドメイン層への命令をつかさどる。<br>
また定期実行の部分についてもここで管理する。<br>

## afb_domain
ビジネスロジックを管理するモジュールで、afb_webやafb_batchから呼び出すことで動作をする。

## afb_infra
DBやTwitterAPIなどと連携するクラスが実装されている。

# 環境構築
## 必要なもの
- postgresql
- java8
- kotlin
- gradle
- twitterアプリケーション

## 準備
- afb_web/src/main/resources/application.properties
- databaseとテーブル作成

## 実行方法
### afb_web

```
$ ./gradlew run
```

### afb_batch
