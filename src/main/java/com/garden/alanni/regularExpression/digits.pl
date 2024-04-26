#!/usr/bin/perl
use strict;
use warnings;

my $result = <STDIN>;
if ($result =~ m/^[0-9]+$/) {
    print "only digits\n";
} else {
    print "not only digits\n";
}