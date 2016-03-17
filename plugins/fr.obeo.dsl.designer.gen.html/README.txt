Known bugs and limitations :
============================
- Clicking on an already selected element in the model explorer does not do anything
  (For example, click on a diagram to display it, open another diagram via the properties ,and reclick  on the first diagram)
- For each semantic element a properties page is generated
  For each DRepresentationElement whose associated semantic elements are different from target, a properties page is generated.
  When a DRepresentationElement has only one associated semantic element which is different from target
  we could avoid the properties page generation and reuse the semantic element's properties page 

Features which could be interesting :
=====================================
- In Model explorer, models are sorted like the "models" feature on a DAnalysis,
  we could sort them like in the real Model Explorer view
- In Model explorer, representations order is not the same as in the real Model Explorer view
- We could add a filter on the Model explorer to mimic the real Model explorer view.
  (see https://groups.google.com/forum/?fromgroups=#!topic/dynatree/1zaKB0BZO0Y via "google dynatree filter")
- Add icons on lines in tables (it could be different from the semantic element's icon)
- The zoom/unzoom panel for diagrams could be prettier
- Add viewpoint selection to filter representations within the Model explorer
  (filtered representations should then not be available in properties page neither) 
- Add viewpoints in model  explorer to group the corresponding representations
  (like in real Model explorer below a representations file) 
- Take cells alignment/style into account for tables
- Add lazy loading on the model explorer tree for best loading time with huge models
  For example, we could extract data into json files 
