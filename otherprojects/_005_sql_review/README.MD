写sql语句容易出错，比如说columnName和数据类型之间忘加空格了。
还有，insert语句在插入text的时候，要把待插入的text用单引号包起来。
还有，insert语建议使用这种，其实在面对复杂数据的时候，确实也需要这种方式

db.execSQL(insert,new Object[]{name,nick,blobData,myChar,mDouble,timestamp});