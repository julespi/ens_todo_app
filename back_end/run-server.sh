#!/bin/bash

# Get the Gradle and Java versions
GRADLE_VERSION="$(exec /opt/gradle/latest/bin/gradle -v | grep Gradle | cut -d " " -f 2)"
JAVA_VERSION="$(java -version 2>&1 | head -n 1 | awk -F '"' '{print $2}' | cut -b 1-5)"

# Color Terminal
red=`tput setaf 1`
green=`tput setaf 2`
reset=`tput sgr0`
white=`tput setaf 7`


function install_java () {
  # Install Java JDK 1.8.0
  apt install openjdk-8-jdk -y
  JAVA_VERSION="$(java -version 2>&1 | head -n 1 | awk -F '"' '{print $2}' | cut -b 1-5)"
}

function install_gradle () {
  # Install Gradle 6.9.1
  # https://linuxize.com/post/how-to-install-gradle-on-ubuntu-20-04/
  VERSION="6.9.1"
  GRADLE_SH="/etc/profile.d/gradle.sh"
  mkdir /tmp
  wget https://services.gradle.org/distributions/gradle-${VERSION}-bin.zip -P /tmp
  unzip -d /opt/gradle /tmp/gradle-${VERSION}-bin.zip
  ln -s /opt/gradle/gradle-${VERSION} /opt/gradle/latest
  touch /etc/profile.d/gradle.sh
  echo "export GRADLE_HOME=/opt/gradle/latest" >> "$GRADLE_SH"
  echo "export PATH=/opt/gradle/latest/bin:\${PATH}" >> "$GRADLE_SH"
  chmod u+x /etc/profile.d/gradle.sh
  source /etc/profile.d/gradle.sh  # Upload Gradle Environment Varibles
  rm /tmp/gradle-${VERSION}-bin.zip
  GRADLE_VERSION="$(gradle -v | grep Gradle | cut -d ' ' -f 2)" # Actualiza
}

function check_gradle {
   # Check Gradle Version
   if [[ "$GRADLE_VERSION" != "command not found" && "$GRADLE_VERSION" == "6.9.1" ]]; then
      return 0
   else
      return 1
   fi
}

function check_java {
	#  Check Jva Version
    if [[ "$JAVA_VERSION" != "command not found" && "$JAVA_VERSION" == "1.8.0" ]]; then
       return 0
    else
       return 1
    fi
}

############################################################
# Run Server                                               #
############################################################
function run_server () {
	apt-get update -y
	apt-get upgrade -y

	echo "${white}Requriments Checking...${reset}"
	
	check_java
	if [ "$?" -eq 0 ]; then
	 echo "${green}Java 8 is already  installed${reset}"
	else
	 echo "${white}Installing Java 8...${reset}"
	 install_java
	 echo "${green}Java 8 Installed${reset}"
	fi
	
	check_gradle
	if [ "$?" -eq 0 ]; then
	 echo "${green}Gradle is already  installed${reset}"
	else
	  echo "${white}Installing Gradle...${reset}"
	  install_gradle
	  echo "${green}Gradle Installed${reset}"
	fi

	echo "${green}All requirements satisfied${reset}"

	# Clean and Build Spring Boot App to a War file
	output="$(exec /opt/gradle/latest/bin/gradle clean build)"
	echo "${green}$output${reset}"

	# Kill All Process Related To Java
	ps -ef | grep "java -jar" | awk '{print $2}' | head -n 1 | xargs kill -9

	# Run Java Spring Boot App
	(nohup java -jar build/libs/ens_todo_app_back-0.0.1-SNAPSHOT.war) &
	echo "${white}Exceute the next command to follow the logger: ${green}tail -f nohup.out${reset}"
}

############################################################
# Help                                                     #
############################################################
function help()
{
   # Display Help
   echo
   echo
   echo "Syntax: run-server.sh [-h|k|r]"
   echo "options:"
   echo "-h     Help."
   echo "-r     Build and Run Spring Boot App."
   echo "-k     Kill all instances of Spring Boot App."
   echo
}

############################################################
############################################################
# Main program                                             #
############################################################
############################################################
while getopts ":rhk" option; do
	case $option in
		r) # Build and Run Spring Boot App
		    run_server
			exit;;
		h) # display Help
			help
			exit;;
		k) # Kill All process
			(ps -ef | grep "java -jar" | awk '{print $2}' | head -n 1 | xargs kill -9)
			echo "${green}Spring Boot App Finished${reset}"
			exit;;
		\?) # Invalid option
			echo "${reed}Error: Invalid option${reset}"
			exit;;
	esac
done

