#!/bin/bash

if [ $# -eq 0 ]; then
	echo "Usage: sc [OPTION] query"
	exit 1

elif [ "$1" == '-h' ] || [ "$1" == '--help' ]; then
	echo "Usage: sc OPTION query"
	echo "Options:"
	echo "	y  : search on youtube"
	echo "	w  : search on wikipedia"
	echo "	b  : search on b-ok.asia"
	echo "	g  : search using google"
	echo "	d  : search using duckduckgo"
	echo "	i  : search in incognito"
	echo "	m  : search on merriam-webster"
	exit 0
fi

[ -n "$BROWSER" ] || BROWSER='firefox'

option=$1
shift 1

query_string=$(echo "$@" | sed 's/ /%20/g')

[ -z "$query_string" ] && exit 1

case $option in
	'y')
	    $BROWSER --new-tab "https://www.youtube.com/results?search_query=${query_string}" > /dev/null 2>&1 & ;;
	'w')
	    $BROWSER --new-tab "https://en.wikipedia.org/wiki/Special:Search?search=${query_string}" > /dev/null 2>&1 & ;;
	'g')
	    $BROWSER --new-tab "https://www.google.com/search?q=${query_string}" > /dev/null 2>&1 & ;;
	'm')
	    $BROWSER --new-tab "https://www.merriam-webster.com/dictionary/${query_string}" > /dev/null 2>&1 & ;;
	'b')
	    $BROWSER --new-tab "https://b-ok.asia/s/${query_string}" > /dev/null 2>&1 & ;;
	'i')
	    $BROWSER --private-window "https://duckduckgo.com/?q=${query_string}" > /dev/null 2>&1 & ;;
	'd')
	    $BROWSER --new-tab "https://duckduckgo.com/?q=${query_string}" > /dev/null 2>&1 & ;;
	*)
	    exit 1 ;;
esac
