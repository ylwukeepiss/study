use strict;
use warnings FATAL => 'all';

my $result = <STDIN>;
$result =~ s/(\.\d\d[1-9]?)\d*/$1/;
print "$result\n";