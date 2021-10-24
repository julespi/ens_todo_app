#!/bin/bash

# Get the NODE NPM versions
NODE_VERSION="$(node --version)"
NPM_VERSION="$(npm -v)"

# Color Terminal
red=`tput setaf 1`
green=`tput setaf 2`
reset=`tput sgr0`
white=`tput setaf 7`


function install_node () {
	# Install NODE 14.x
	curl -fsSL https://deb.nodesource.com/setup_14.x | sudo -E bash -
	apt install nodejs -y
	NODE_VERSION="$(node --version)"
}

function install_npm () {
	# Insall NPM 14.x
	apt install npm -y
	NPM_VERSION="$(npm -v)"
}

function check_node {
	#  Check Node Version
    if [[ "$NODE_VERSION" != "command not found" && "$NODE_VERSION" == "v14.18.1" ]]; then
       return 0
    else
       return 1
    fi
}

function check_npm {
	#  Check NPM Version
    if [[ "$NPM_VERSION" != "command not found" && "$NPM_VERSION" == "6.14.15" ]]; then
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
	
	check_node
	if [ "$?" -eq 0 ]; then
	 echo "${green}Node is already  installed${reset}"
	else
	 echo "${white}Installing Node...${reset}"
	 install_node
	 echo "${green}Node Installed${reset}"
	fi
	
	check_npm
	if [ "$?" -eq 0 ]; then
	 echo "${green}NPM is already  installed${reset}"
	else
	 echo "${white}Installing NPM...${reset}"
	 install_npm
	 echo "${green}NPM Installed${reset}"
	fi
	
	echo "${green}Installing dependencies...${reset}"
	npm install --also=dev
	echo "${green}Dependencies installed${reset}"
	
	# Kill All Process Related To FE
	ps -ef | grep "node" | awk '{print $2}' | head -n 1 | xargs kill -9

	# Running Front End App
	(nohup npm run dev) &
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
   echo "-r     Build and Run Front End App."
   echo "-k     Kill all instances of Front End App."
   echo
}

############################################################
############################################################
# Main program                                             #
############################################################
############################################################
while getopts ":rhk" option; do
	case $option in
		r) # Build and Run Front End App
		    run_server
			exit;;
		h) # display Help
			help
			exit;;
		k) # Kill All process
			(ps -ef | grep "node" | awk '{print $2}' | head -n 1 | xargs kill -9)
			echo "${green}Front End App Finished${reset}"
			exit;;
		\?) # Invalid option
			echo "${reed}Error: Invalid option${reset}"
			exit;;
	esac
done

