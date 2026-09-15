# Hobby

An Android chat app built around interests rather than contacts. You sign in,
pick a topic, and join a live room with everyone else who picked it.

Nine rooms: art, fashion, food, gaming, IT, literature, movies, music and sport.

This was my first Android project, written in 2017 during my first year of
university.

---

## What it does

**Sign in with FirebaseUI**, with the account carried through to the messages so
every line is attributed. Signing out is in the overflow menu.

**A room per topic.** Each is its own node in the Realtime Database, so the rooms
are genuinely separate conversations rather than one feed with a filter.

**Live messages.** `FirebaseListAdapter` binds the list directly to a database
reference, so a message typed on one phone appears on another without a refresh,
a pull, or any polling code. Each message carries its text, the sender's display
name and a timestamp set at construction, rendered as a chat bubble with the
time formatted on the way out.

**Small touches that matter on a phone**: the keyboard dismisses itself after
send, and the input clears, so you can type the next message immediately.

---

## Stack

Java, Android, Firebase Auth via FirebaseUI, Firebase Realtime Database,
`FirebaseListAdapter` for the live binding, and a bubble view library for the
message rendering.

It is pinned to 2017 tooling: `compile` rather than `implementation`, support
library 26, Firebase 10. Expect to update the build before it compiles on a
current machine.

---

## Running it

1. Create a Firebase project and add an Android app with the package name
   `com.example.corina.hobby`.
2. Enable Authentication, and create a Realtime Database.
3. Put your own `google-services.json` in `app/`.
4. Set database rules so that only signed in users can read and write the topic
   nodes.
5. Open in Android Studio and run.

The nine topic nodes are created on first write, so there is nothing to seed.

---

## Layout

```
app/src/main/java/com/example/corina/hobby/
  MainActivity.java    landing screen
  Subjects.java        sign in, sign out, and the nine topic buttons
  ChatMessages.java    the message model: text, sender, timestamp
  ArtChat.java         one room
  FashionChat.java     ...
  FoodChat.java        ...
  GamingChat.java      ...
  ItChat.java          ...
  LiteratureChat.java  ...
  MoviesChat.java      ...
  MusicChat.java       ...
  SportChat.java       ...
```

That list is the first thing I would change, and the next section says why.

---

## What I would do next

**Nine activities should be one.** `ArtChat` and `MusicChat` differ in three
places: the layout they inflate, the view ids they look up, and the name of the
database node they read. Everything else is copied. One `ChatActivity` taking
the topic as an intent extra, with a single shared layout, would replace roughly
seven hundred lines with eighty. This is the clearest thing in any of my
repositories that I would do differently now, and I would rather point at it
than quietly delete it.

**Move the listener off the adapter's lifetime.** `FirebaseListAdapter` keeps
listening until it is cleaned up, and nothing here calls `cleanup()` in
`onDestroy`, so leaving a room leaves the listener attached.

**Limit the query.** The adapter binds to the whole node, so a room with ten
thousand messages loads ten thousand messages. `limitToLast` would fix that in
one line.

**Modernise.** AndroidX, current Firebase, `implementation` instead of the long
removed `compile`, and a `RecyclerView` in place of `ListView`.

**The comments are in Romanian**, and several explain what an `Intent` is,
because I wrote them while learning. I have left them: this repository is nine
years old and pretending otherwise would be worse than the comments.
