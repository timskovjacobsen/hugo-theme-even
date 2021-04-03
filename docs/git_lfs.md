
# Git-LFS

Git Large File System

* Stores tiny reference files to the actual files, which are stores on a server somewhere else

Suppose you have png files in a repo and are tracking them by Gti-LFS. Every new version of each file is stored on the external server, while the repository only stores lightweight pointers to it. These pointers are in the size of a few bytes.

* cloning a repository will copy the latest version of each png file to your local working directory while keeping previous version in the form of the pointers

* Checking out branches will also download any latest versions that are not available locally provided that an internet connection is established

The key point is that only the latest versions are getting downloaded and not every version. For binary files, 10 versions will be approx. 10x file size.

Git-LFS comes as part of the *Git for Windows* installer, so it works out of the box.
