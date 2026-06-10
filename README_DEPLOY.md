# Ubuntu 24.04 Docker 部署说明

本项目通过 Docker Compose 启动两个容器：

- `mysql`: MySQL 8.4，数据持久化到 Docker volume `mysql_data`
- `app`: Spring Boot 应用，默认监听 `7070`

## 1. 服务器准备

在 Ubuntu 24.04 上安装 Docker 和 Compose 插件：

```bash
sudo apt update
sudo apt install -y ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc
echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt update
sudo apt install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
sudo systemctl enable --now docker
```

可选：允许当前用户直接执行 Docker 命令：

```bash
sudo usermod -aG docker $USER
newgrp docker
```

## 2. 上传项目

将项目上传到服务器，例如：

```bash
scp -r ./git-test user@server_ip:/opt/git-test
```

进入项目目录：

```bash
cd /opt/git-test
```

## 3. 配置环境变量

复制环境变量模板：

```bash
cp .env.example .env
```

编辑 `.env`，至少修改：

```env
MYSQL_ROOT_PASSWORD=请改成强密码
MYSQL_PASSWORD=请改成强密码
```

如服务器已有 MySQL 占用 `3306`，可以改为：

```env
MYSQL_HOST_PORT=3307
```

应用端口默认是 `7070`，如需调整：

```env
APP_HOST_PORT=7070
```

## 4. 启动

```bash
chmod +x deploy/deploy.sh
./deploy/deploy.sh
```

也可以直接执行：

```bash
docker compose up -d --build
```

查看状态和日志：

```bash
docker compose ps
docker compose logs -f app
docker compose logs -f mysql
```

## 5. 访问验证

浏览器或接口工具访问：

```text
http://server_ip:7070/user/getAllUser
```

如果改过 `APP_HOST_PORT`，将 `7070` 替换为对应端口。

## 6. 常用运维命令

重启：

```bash
docker compose restart
```

停止：

```bash
docker compose down
```

重新构建应用：

```bash
docker compose up -d --build app
```

进入 MySQL：

```bash
docker compose exec mysql mysql -uroot -p
```

备份数据库：

```bash
docker compose exec mysql mysqldump -uroot -p gittest > gittest.sql
```

注意：`docker compose down -v` 会删除数据库 volume，除非确认不再需要数据，否则不要执行。
