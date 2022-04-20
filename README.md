# yanig
Yet another image gallery

- Should follow MVP approach: Create an MVP and work on the extra features later

#MVP
- Should be a static generator
- Minimum configuration
  - Give it a rood folder and it will do the work
- JPG+RAW aware - no dups
- Generation should be incremental
  - have a database
  - scan files, find the diffs against database and process
- Inside the folder order by
  - file name
  - date/time taken (EXIF)
- Display modes
  - tiles
  - Single photo (film roll)
- Thumbnails generation for the tiles mode
  - what size?
- Mobile friendly
- Handle large folders well
- what about videos?

#Extra features
- Configuration files customizing things like
  - Exclude certain files from the gallery
  - Mark files "private"
- Support "back of the photo" and custom comments.
- Support Albums - photos from different folders together
- Access restrictions
  - Authentication
  - Group of the users, authorization
- Share folders
  - Using password
  - Using a private link
- Download a files + as an archive
- UI to manage the library and generation settings

#Design process
- Embedded database/index?
- library for thumbnails generation
- library for EXIF reading
- Thumbnails follow the same folders structure or disconnected? Colocated with static html files?
- How much javascript?
- Lazy loading?
