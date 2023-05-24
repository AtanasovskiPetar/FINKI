if [ "$#" -ne 2 ]; then
    echo "Potrebno e da vnesite 2 argumenti: izvoren imenik i destinaciski imenik"
    exit 1
fi

echo "Vnesivte tochno 2 argumenti"

source_folder="$1"
destination_folder="$2"

# Create the destination folder if it doesn't exist
mkdir -p "$destination_folder"

total_size=0
for file in "$source_folder"/*.txt; do
    filename=$(basename "$file")
    extension="${filename##*.}"
    if [[ "$extension" = "txt" && $filename =~ ^[a-z].* ]]; then
        new_filename="${filename%.txt}.moved_txt"
        size=$(du -b "$file" | awk '{print $1}')
        total_size=$((total_size + size))
        mv "$file" "$destination_folder/$new_filename"
    fi
done

echo "Total file size: $total_size bytes"