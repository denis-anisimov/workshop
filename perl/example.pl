while (<>) {
    chomp;             # remove newline
    print;             # print the line
    print "\n" if eof; # at end of file, print a newline
}

while (<>) {
    print "line $. : ", $_;
    close ARGV if eof;
}