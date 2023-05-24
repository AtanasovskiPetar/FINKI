find "$HOME" -mtime -1 -type f | cut -d '/' -f 4 > modified.txt

