echo $ENCODED_KEYSTORE | base64 --decode >> ${HOME}/DailyBugle/keystore.jks
echo 'export STORE_FILE=${HOME}/DailyBugle/keystore.jks' >> $BASH_ENV