#!/bin/bash
//check path for this configuration to your "intservlet" project path
PROG_OPT="--port=8888 /home/milax/Baixades/google_app_engine/intservlet/war"

//do NOT modify these lines
OPT="-cp /usr/lib/jvm/java-6-openjdk-i386:/home/milax/.eclipse/org.eclipse.platform_3.8_155965261/plugins/com.google.appengine.eclipse.sdkbundle_1.7.3/appengine-java-sdk-1.7.3/lib/appengine-tools-api.jar \
-Ddatastore.default_high_rep_job_policy_unapplied_job_pct=50 \
-Xmx512m \
-javaagent:/home/milax/.eclipse/org.eclipse.platform_3.8_155965261/plugins/com.google.appengine.eclipse.sdkbundle_1.7.3/appengine-java-sdk-1.7.3/lib/agent/appengine-agent.jar"
MAIN="com.google.appengine.tools.development.DevAppServerMain"

java $OPT $MAIN $PROG_OPT
