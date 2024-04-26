use strict;
use warnings FATAL => 'all';

my $result = <STDIN>;
$result =~ s/^Jeff$/Jeffery/i;
print "$result\n";
