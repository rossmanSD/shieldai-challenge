# Intro

Welcome to Ross' solution for the _Shield AI Challenge_. Ultimately,
this solution is far from perfect, but I will attempt to explain some of
my decisions in this document as well as in comments throughout the
codebase.

# Demo

I made a simple screenrecording of the execution of the app in an
emulator running API level 31. Please see the file
`ross_challenge_demo.mp4` in the root directory of this project.

# Design and Implementation

In general, I attempted to follow the MVVM pattern by grouping concerns
into View (Activities, Fragments, etc), ViewModel (the viewmodels), and
Repository layers.

I decided to use `StateFlow` In lieu of `LiveData`. Either would have
worked fine. But I've been migrating my projects to `StateFlow` for many
reasons, including (1) `StateFlow` requires a default value, so we don't
need null checks in every observer; and (2) simplified code since there
is no distinction between `setValue()` and `postValue()` as with `LiveData`.
That is, `StateFlow` is thread safe.

I decided to use a combination of Fragments, Constraint Layouts, and
RecyclerViews. I did not use Jetpack Compose because I am not familiar
with it at all, and I didn't have the time necessary to do it justice
during this challenge. I was actually pretty rusty implementing a
RecyclerView, and I suspect there is a lot of optimization I could do
there if I had more time. I spent a lot of time getting the RecyclerView
and associated Adapter to work correctly.

For the most part, my implementation meets the majority of the given
requirements. Both the MVP and Part B are implemented. And the app loads
the last episode that was selected during the prior run.

One note: I copied the json input file to the assets directory as I
didn't know an easy to to load a file from `res/raw`. I ran out of time
to investigate this further.

# Testing

I did include a unit test of the json inflating logic to make sure it
was working. The remainder of the testing was done manually using
multiple devices. I wanted to incluide some automated UI tests, but I
ran out of time to implement them.

I think the use of dependency injection and layered design should allow
for the creation of a lot more unit and instrumentation tests.

# 3rd Party Libraries

External libraries can make Kotlin/Android development a lot easier. In
turn, I decided to make use of the following libraries:

## logcat by Square

logcat makes it really easy to add logging, and precludes the need to
gate log statements with an `if` clause. Read more
[here](https://github.com/square/logcat).

## Coil, an image loading library

[Coil](https://coil-kt.github.io/coil/) is a simple image loading
library backed by coroutines. Similar to Glide, Picasso, etc.

## Koin, a smart Kotlin injection library

I like libraries that get the job done and get out of the way. That's
why I like [Koin](https://insert-koin.io/) for dependency injection a
lot.

## GSON for json parsing

There's lots of ways to parse json data these days, but I still like
using the Gson library.
