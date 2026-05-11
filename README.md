# Simple Bulletin Board Application　簡易的な掲示板アプリ

## Overview　概要

This is a simple bulletin board application with CRUD functionality and user authentication.
It allows users to create, read, update, and delete posts after logging in.

簡易的なCRUD機能とユーザー認証を搭載した掲示板アプリ。
ユーザーに投稿、閲覧、更新、削除をログイン後可能にする。

## Tech Stack　使用技術

* Java
* Spring Boot
* H2 Database (or MySQL)
* Spring Security

## Features　仕様

* User registration and login (unique user)
* Create posts
* View post list
* Edit posts
* Delete posts
* Only the author can edit or delete their own posts

* ユーザー認証とログイン （ユーザー名重複禁止）
* 投稿作成
* 掲示板閲覧
* 投稿編集・削除
* 投稿者のみが編集・削除

## Key Points　特徴

* Designed based on MVC architecture
* Implemented user authentication and authorization
* Established relationships between users and posts
* Prevented unauthorized operations (users can only modify their own posts)

* MVCアーキテクチャに基づいた設計
* ユーザー認証（Authentication）および認可（Authorization）の実装
* ユーザーと投稿間におけるリレーションシップの構築
* 権限管理の徹底（ユーザー本人以外の投稿編集・削除を制限）

## Future Improvements　今後の改善点

* Convert to REST API
* Separate frontend (e.g., React)
* Improve UI/UX
* Add validation and error handling

* REST APIへの移行
* フロントエンドの分離（Reactなどの導入）
* UI/UXの向上・改善
* バリデーション（入力チェック）およびエラーハンドリングの追加

## Motivation　動機

This project was built to practice backend development, including authentication, database design, and CRUD operations.

このプロジェクトは、認証機能、データベース設計、およびCRUD操作を含むバックエンド開発の習得を目的として作成しました。
