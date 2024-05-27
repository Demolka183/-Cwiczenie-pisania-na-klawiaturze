# -Ćwiczenie-pisania-na-klawiaturze

Projekt TypingPractice to aplikacja do ćwiczenia pisania na klawiaturze. Użytkownik losuje zdanie do przepisania, wpisuje je w pole tekstowe, a po naciśnięciu klawisza Enter aplikacja sprawdza poprawność wpisanego tekstu, oblicza procent błędów i czas wpisywania. Wyniki są wyświetlane użytkownikowi wraz z możliwością rozpoczęcia nowej próby.

0. Spis treści

    Importy  
    Deklaracje klas i zmiennych
    Konstruktor TypingPractice
    Metoda loadSentences
    Metoda setupUI
    Metoda startNewTry
    Metoda confirmText
    Metoda checkErrors
    Metoda showErrors
    Metoda main

1. Lista i krótki opis zaimplementowanych w projekcie funkcjonalności

    1.Importy
        Importuje niezbędne klasy z bibliotek Swing, AWT, IO, NIO, Util i Streams do stworzenia GUI i operacji na plikach.

    2. Deklaracje klas i zmiennych
        -TypingPractice dziedziczy po JFrame.
        -Prywatne zmienne:
            -List<String> sentences: przechowuje listę zdań do ćwiczenia.
            -String currentSentence: aktualne zdanie do przepisania.
            -JLabel sentenceLabel: etykieta wyświetlająca zdanie do przepisania.
            -JTextField inputField: pole tekstowe do wpisywania przez użytkownika.
            -JTextPane outputPane: obszar tekstowy do wyświetlania wyników.
            -long startTime: czas rozpoczęcia pisania.
            -boolean hasStartedTyping: flaga oznaczająca rozpoczęcie pisania.

    3. Konstruktor TypingPractice
        Inicjuje obiekt TypingPractice, ładuje zdania z pliku i ustawia interfejs użytkownika.

    4. Metoda loadSentences
        Wczytuje zdania z pliku tekstowego do listy sentences. W przypadku błędu wyświetla komunikat i zamyka aplikację.

    5. Metoda setupUI
        Ustawia tytuł okna, domyślne operacje zamknięcia, rozmiar i układ.
        Inicjalizuje i dodaje komponenty GUI (etykietę, pole tekstowe, obszar wyjściowy, przycisk startowy).
        Dodaje nasłuchiwacza klawiatury do pola tekstowego.

    6. Metoda startNewTry
        Rozpoczyna nową próbę: losuje nowe zdanie, resetuje pole tekstowe i obszar wyjściowy, ustawia fokus na pole tekstowe, resetuje flagi i czas.

    7. Metoda confirmText
        Oblicza czas zakończenia, pobiera wpisany tekst, sprawdza błędy, oblicza procentową poprawność i czas wpisywania.
        Wyświetla wyniki i pyta użytkownika o ponowną próbę.

    8. Metoda checkErrors
        Sprawdza błędy w wpisanym tekście, porównując go z oryginałem i zwraca liczbę błędów.

    9. Metoda showErrors
        Wyświetla błędy w obszarze wyjściowym, używając różnych stylów dla poprawnych i błędnych znaków.

    10. Metoda main
        Uruchamia aplikację w wątku Swing, tworząc nową instancję TypingPractice.




1. Instrukcja obsługi aplikacji TypingPractice
     Wymagania wstępne

      Zainstalowana Java Development Kit (JDK) wersja 8 lub nowsza.
      Plik tekstowy sentences.txt zawierający zdania do przepisania, umieszczony w tym samym katalogu co plik wykonywalny aplikacji.

  1. Jak uruchomić aplikację

      Pobierz kod źródłowy z repozytorium i rozpakuj go do osobnego folderu:
   
  3. Kompilacja i uruchomienie aplikacji:

      Otwórz terminal/wiersz poleceń i przejdź do katalogu, w którym znajduje się plik TypingPractice.java.
      Skorzystaj z poniższych poleceń, aby skompilować i uruchomić aplikację:
          javac TypingPractice.java
          java TypingPractice
   
  5. Obsługa aplikacji

     1. Uruchomienie programu:
          Po uruchomieniu aplikacji pojawi się okno z komunikatem "Kliknij 'Start' aby rozpocząć".
          Upewnij się, że w tym samym katalogu co aplikacja znajduje się plik sentences.txt zawierający zdania do przepisania.

      2. Rozpoczęcie ćwiczenia:
          Kliknij przycisk Start znajdujący się po prawej stronie okna.
          Pojawi się nowe zdanie do przepisania, a pole tekstowe zostanie odblokowane.

      3. Wpisywanie tekstu:
          Zacznij przepisywać zdanie w polu tekstowym. Czas będzie mierzony od momentu rozpoczęcia pisania.
          Gdy skończysz, naciśnij klawisz Enter.

      4. Sprawdzenie wyników:
          Po naciśnięciu klawisza Enter aplikacja automatycznie sprawdzi poprawność wpisanego tekstu.
          Wynik, w tym procentowa poprawność i czas potrzebny na przepisanie zdania, zostanie wyświetlony w oknie dialogowym.
          Błędy zostaną podkreślone na czerwono w dolnym polu tekstowym.

      5. Ponowna próba:
        Po wyświetleniu wyników aplikacja zapyta, czy chcesz spróbować ponownie. Kliknij Yes, aby rozpocząć nową próbę, lub No, aby zakończyć.
