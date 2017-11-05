java -jar -Xms512M -Xmx512M -Xloggc:UseSerialGC.log -XX:+UseSerialGC target/OtusL04.jar
java -jar -Xms512M -Xmx512M -Xloggc:UseParallelGC.log -XX:+UseParallelGC target/OtusL04.jar
java -jar -Xms512M -Xmx512M -Xloggc:UseParallelOldGC.log -XX:+UseParallelOldGC target/OtusL04.jar
java -jar -Xms512M -Xmx512M -Xloggc:UseConcMarkSweepGC.log -XX:+UseConcMarkSweepGC target/OtusL04.jar

java -cp target/OtusL04.jar ru.otus.l04.calculator.GCActivityCalculator