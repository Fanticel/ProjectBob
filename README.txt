For future reference typeIDs are:
-1 - general project
0 - Residential
1 - Commercial
2 - Road
3 - Industrial

ArrayList<Object> Data looks like:
{int typeID, String name, String description, int expectedTotalHours, int expectedExpenses, long budget, MyDate timeline}
  0           1             2                   3                       4                     5           6
Type specific data are appended in order of the constructor.