import java.util.ArrayList;
//Arda
public class Search implements Filter, Sort {

    @Override
    public ArrayList<Book> filter() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Book> sortByAscendingName() {
        ArrayList<Book> sortedList = bookList;
        Book temp;
        boolean changed = true;
        for (int i = 0; i < sortedList.size() && changed; i++) {
            changed = false;
            for (int j = 0; j < sortedList.size() - i; j++) {
                if (sortedList.get(j).getName().compareToIgnoreCase(sortedList.get(j + 1)) < 0){
                    changed = true;
                    temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        return sortedList;
    }

    @Override
    public ArrayList<Book> sortByAscendingRating() {
        ArrayList<Book> sortedList = bookList;
        Book temp;
        boolean changed = true;
        for (int i = 0; i < sortedList.size() && changed; i++) {
            changed = false;
            for (int j = 0; j < sortedList.size() - i; j++) {
                if (sortedList.get(j).getRating() > sortedList.get(j).getRating()){
                    changed = true;
                    temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        return sortedList;
    }

    @Override
    public ArrayList<Book> sortByDescendingName() {
        ArrayList<Book> sortedList = bookList;
        Book temp;
        boolean changed = true;
        for (int i = 0; i < sortedList.size() && changed; i++) {
            changed = false;
            for (int j = 0; j < sortedList.size() - i; j++) {
                if (sortedList.get(j).getName().compareToIgnoreCase(sortedList.get(j + 1)) > 0){
                    changed = true;
                    temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        return sortedList;
    }

    @Override
    public ArrayList<Book> sortByDescendingRating() {
        ArrayList<Book> sortedList = bookList;
        Book temp;
        boolean changed = true;
        for (int i = 0; i < sortedList.size() && changed; i++) {
            changed = false;
            for (int j = 0; j < sortedList.size() - i; j++) {
                if (sortedList.get(j).getRating() < sortedList.get(j).getRating()){
                    changed = true;
                    temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        return sortedList;
    }
}

