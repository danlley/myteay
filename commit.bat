title GIT提交批处理——魏小敏
color 16
@echo off
echo *************************************************************
echo *                GIT 代码提交批处理                         *
echo *  Author: 魏小敏                                           *
echo *  Version: 1.0                                             *
echo *  Created: 2017/3/26                                       *
echo *  Last Updated by: 魏小敏                                  *
echo *************************************************************

echo;
echo;

echo 开始提交代码到本地仓库
echo 当前目录是：%cd%

echo;
echo;
echo 开始识别当前git的版本
echo;
git --version
echo;
echo;

git add -A .

git commit -m "修改代码"


git push origin master











pause








