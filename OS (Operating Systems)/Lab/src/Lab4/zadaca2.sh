if [ "$#" -ne 2 ]; then
    echo "Usage: $0 <source_folder> <destination_folder>"
    exit 1
fi

source_folder="$1"
destination_folder="$2"

# Create the destination folder if it doesn't exist
mkdir -p "$destination_folder"

# Move .txt files with names consisting of only small letters
for file in "$source_folder"/*.txt; do
    filename=$(basename "$file")
    extension="${filename##*.}"
    if [[ "$extension" = "txt" && $filename =~ ^[a-z].* ]]; then
       new_filename="${filename%.txt}.moved_txt"
        mv "$file" "$destination_folder/$new_filename"
#       echo "Moved $filename to $destination_folder/$new_filename"
    fi
done