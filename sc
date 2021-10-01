#!/bin/bash

if [ $# -eq 0 ]; then
	echo "Usage: sc [OPTION] query"
	exit 1

elif [ "$1" == '-h' ] || [ "$1" == '--help' ]; then
	echo "Usage: sc -[OPTION] query"
	echo "Options:"
	echo "	-g  : search using google"
	echo "	-x  : search using searx"
	echo "	-xi : search using searx for images"
	echo "	-i  : search in incognito"
	echo "	-wi : search on wikipedia"
	echo "	-yt : search on youtube"
	echo "	-b  : search on b-ok.asia"
	echo "	-m  : search on merriam-webster"
	exit 0

fi

# remove -flag if any, replace ' ' with +
query_string=$(echo $@ | sed -e 's/^-[a-z]* *//; s/\s/\+/g')

# for b-ok.asia
alt_query_string=$(echo $@ | sed -e 's/^-[a-z]* *//')

[ -z "$query_string" ] && exit 1

case "$1" in
	'-x')
		$BROWSER --new-tab "https://searx.bar/search?q=${query_string//\+/%20}&categories=general&language=en-US" > /dev/null 2>&1 & ;;
	'-xi')
		$BROWSER --new-tab "https://searx.bar/search?q=${query_string//\+/%20}&categories=images&language=en-US" > /dev/null 2>&1 & ;;
	'-g')
		$BROWSER --new-tab "https://www.google.com/search?q=${query_string}" > /dev/null 2>&1 & ;;
	'-d')
		$BROWSER --new-tab "https://duckduckgo.com/?q=${query_string}" > /dev/null 2>&1 & ;;
	'-wi')
		$BROWSER --new-tab "https://en.wikipedia.org/wiki/Special:Search?search=${query_string}" > /dev/null 2>&1 & ;;
	'-yt')
		$BROWSER --new-tab "https://www.youtube.com/results?search_query=${query_string}" > /dev/null 2>&1 & ;;
	'-i')
		$BROWSER --private-window "https://duckduckgo.com/?q=${query_string}" > /dev/null 2>&1 & ;;
	'-b')
		$BROWSER --new-tab "https://b-ok.asia/s/${alt_query_string}" > /dev/null 2>&1 & ;;
	'-m')
		$BROWSER --new-tab "https://www.merriam-webster.com/dictionary/${query_string}" > /dev/null 2>&1 & ;;
	*)
		$BROWSER --new-tab "https://duckduckgo.com/?q=${query_string}" > /dev/null 2>&1 & ;;
esac

