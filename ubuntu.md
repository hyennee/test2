# 우분투(Ubuntu) 서버 설치 및 설정

## USB 포맷

UBS(Universal Serial Bus) 저장 매체를 FAT32(File Allocation Table 32) 로 포맷.

## Ubuntu ISO 다운로드

[Download Ubuntu Server](https://ubuntu.com/download/server) Ubuntu 20.04.3 LTS(Long Term Support) 다운로드.

## 부팅 가능한 USB 설치 디스크 만들기

* [balenaEtcher](https://www.balena.io/etcher/) 앱을 사용하여 부팅 디스크 만들기(맥).
* [Refus](https://rufus.ie/) 앱을 사용하여 부팅 디스크 만들기(윈도우).

-----

## 우분투 설치 버전 확인

```bash
# OS 버전 확인
cat /etc/issue              # Ubuntu 20.04.3 LTS \n \l
lsb_release -a | grep Desc  # Description: Ubuntu 20.04.3 LTS
# 커널 버전 확인
uname -r                    # 5.4.0-81-generic
```

-----

# 네트워크 확인

랜카드 인식 여부 확인.

```bash
lspci | grep -i Ethernet
```

랜카드의 네트워크 상태 확인.

```bash
lshw -class network
```

`*-network UNCLAIMED`와 같이 출력되면 네트워크를 사용하지 못하는 상태.

-----

## `root` 비밀번호 설정

```bash
sudo passwd root
```

* root Password: !q2w3e4r

-----

## 패키지 목록 업데이트

`apt`(Advanced Packaging Tool) 패키지 목록 업데이트.

```bash
sudo apt update
```

`apt` 패키지들 업그레이드.

```bash
sudo apt upgrade
```

-----

## `sshpass` 설치

```bash
sudo apt-get install sshpass
```

## `net-tools` 설치

우분투 네트워크 관련 툴인 `net-tools` 설치.

```bash
sudo apt install net-tools
ifconfig
```

-----

## Java 설치

```bash
sudo apt-get install openjdk-8-jre
```

### Java 환경변수 설정

```bash
sudo vi /etc/environment
```

`JAVA_HOME` 추가.

```bash
JAVA_HOME="/usr/lib/jvm/java-8-openjdk-amd64"
```

변경된 내용 적용.

```bash
source /etc/environment
echo $JAVA_HOME
```

-----

## NginX 설치

웹/프록시 서버를 위한 NginX 설치.

```bash
sudo apt install nginx
```

### NginX 서비스 관련 명령

```bash
sudo service nginx start      # NginX 서비스 시작
sudo service nginx status     # NginX 서비스 상태
sudo service nginx reload     # NginX 설정 파일 적용(서버 중단되지 않음)
sudo service nginx restart    # NginX 서비스 재시작(서버 중지 후 재시작)
sudo service nginx stop       # NginX 서비스 중지

nginx -s reload               # NginX 설정 파일 적용(서버 중단되지 않음)
nginx -s stop                 # NginX 서비스 중지
nginx -s reopen               # NginX 로그 파일 다시 열기
```

### NginX 설정

```bash
cat /etc/nginx/nginx.conf
```

### NginX 수신 상태 확인

```bash
netstat -lntp
```

환경설정 파일(`/etc/nginx/nginx.conf`)에서 지정한 포트를 수신 중인지 확인.

-----

## Node 설치

### NVM(Node Version Manager) 설치

```bash
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.0/install.sh | bash
```

또는

```bash
wget -qO- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.0/install.sh | bash
```

설치 경로.

```bash
~/.nvm
```

NVM 경로 설정 및 로드는 `~/.bashrc`, `~/.profile`, `~/.bash_profile`, `~/.zshrc` 파일 중 하나에 추가됨.
각각의 파일을 확인하여 NVM과 관련된 내용이 추가된 파일을 적용.

```bash
source ~/.bashrc
```

### Node 설치

Node 안정화 버전(LTS: Long Term Support)의 최신 버전 확인.

```bash
nvm version-remote --lts
```

Node 안정화 버전(LTS: Long Term Support)의 최신 버전 설치.

```bash
nvm install --lts
```

Node 버전 확인.

```bash
node -v
npm -v
```

-----

## Git 설치

설치되어 있지 않을 경우 설치.

```bash
sudo apt-get install git
```

-----

## MariaDB 설치(필요한 경우에만 설치)

### MaraiDB Server 설치

```bash
sudo apt install mariadb-server
```

### MariaDB 클라이언트 모듈 설치(MariaDB 접속을 위한 다양한 도구)

```bash
sudo apt-get install mariadb-client
```

### MariaDB 계정 및 보안 설정

```bash
sudo mysql_secure_installation
```

`root` Password: 1qaz2wsx#EDC

### MariaDB 접속

```bash
sudo mysql -uroot -p1qaz2wsx#EDC
```

* ATA(BizTalk의 알림톡 에이전트)를 위한 데이터베이스 생성 및 사용자 계정 생성 정보는 [MariaDB.md](./MariaDB.md) 참고.

-----

## `root` 계정으로 SSH 접근 허용

```bash
su
vi /etc/ssh/sshd_config
```

설정 내용 중 `PermitRootLogin`의 값을 `yes`로 변경 후 저장.

`ssh` 서비스 재시작

```bash
service ssh restart
```

-----

## 타임존 설정

```bash
# 시간 확인
date
# 날짜, 시간, 타임존, 타임서버와의 동기화 여부 확인
timedatectl
```

타임존이 `Asia/Seoul`이 아닐 경우 `Asia/Seoul`로 설정.

```bash
# 설정 가능한 타임존 확인
timedatectl list-timezones | grep Seoul
# 타임존 설정
sudo timedatectl set-timezone Asia/Seoul
# 시간 동기화 서버와 날짜 시간 동기화를 사용하도록 설정
sudo timedatectl set-ntp yes
```

-----

## 네트워크 설정 확인

```bash
# 주소 결정 프로토콜(Address Resolution Protocol - ARP)
arp

# IP 라우트 확인
ip route

# IP 라우트 확인
route

# 네트워크 인터페이스 설정 확인
ifconfig
ip a
ip addr

# 네트워크 상태 확인
# -l (Listen): 연결 가능한 상태
# -n (Number Port): 포트
# -t (TCP): TCP
# -p (PID): 프로세스 이름
# -u (UDP): UDP
# -a (All): 모두
# -i (Ithernet): 이더넷 카드별 상태 및 패킷 수 확인
# -r (Routing): 라우팅 테이블
# -s (Statistics): 네트워크 통계

# 연결을 기다리는 목록과 프로세스 확인
netstat -nap
# 특정 포트가 사용 중인지 확인
netstat -an | grep <포트번호>
# TCP 수신 상태의 포트와 프로세스 확인
netstat -lntp
```

### 고정 IP 설정

```bash
sudo vi /etc/netplan/00-installer-config.yaml

```

```yaml
network:
  ethernets:
    eno1:
      addresses:
        - <고정 IP 주소>/24
      gateway4: <게이트웨이 IP 주소>
      nameservers:
        addresses: [<네임서버 주소>]
    eno2:
      dhcp4: true
  version: 2
```

enx00e04c683982

변경된 내용 적용

```bash
sudo netplan try
sudo netplan apply
```

-----
