echo "Homework 1"
# find . -name 'CS3620*'
find . -type f -print0 | while IFS= read -r -d $'\0' file;
	do if [[ "$file" =~ ./CS3620* ]] ; then
		printf "bmcano" >> "$file" ;
	fi
done
echo "Done"
