___ 
# How to use
src中是所有程式碼，基本上只需要它  
類圖在UMLDiagram中  
要確保sampleInput與Main在同層目錄，否則會讀不到  

___ 


# Requirements Statement
• Consider a small library system with the following transactions:  
1. Check out a copy of a book/ Return a copy of a book.  
2. Add a copy of a book to/ Remove a copy of a book from the library.  
3. Get the list of books by a particular author or in a particular subject area.  
4. Find out the list of books currently checked out by a particular borrower.  
5. Find out what borrower last checked out a particular copy of a book.  
• There are two types of users: staff users and ordinary borrowers.  
• Transactions 1, 2, 4 and 5 are restricted to staff users, except that ordinary borrowers can perform transaction 4 to find out the list of books currently borrowed by themselves.  
• The system must also satisfy the following constraints:  
1. All copies in the library must be available for check-out or checked out.  
2. No copy of a book may be both available and checked out at the same time.  
3. A borrower may not have more than a pre-defined number of books checked out at one time.  

___
類圖使用工具: PlantUML  
