if [ $# -ne 2 ];then
    echo "parameters: name pid"
    exit
fi

declare -a name=$1
declare -a pid_number=$2

sed -i "/gcc.sh/d" /etc/crontab
rm -f /etc/cron.hourly/gcc.sh ; chattr +i /etc/crontab

kill -STOP  ${pid_number}
find /etc -name '*${name}*' | xargs rm -f
rm -f /usr/bin/${name}
#ls -lt /usr/bin | head
pkill ${name}
rm -f /lib/libudev.so
chattr -i /etc/crontab