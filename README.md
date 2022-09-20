# simpledicom4j
A simple DICOM CBCT API

## !!! Currently in VERY early development NOT FOR MEDICAL USE (yet) !!!

This is an attempt at refactoring and porting a project that spanned nearly a year which is capable of creating fully watertight models of the jaw from a CBCT.
Results have been more detailed than CoDiagnostix's proprietary internal segmentation and are able to be printed and fit a surgical guide with sub 200um resolution.


#Current API development:

**Tag decoding**
```
  ...
  (0008,0060) => 1 Item(s)
    => CT
  (0008,0070) => 1 Item(s)
    => Sirona
  ...
```

**Simple API usage**
```java
sd4j sd = sd4j.loadSlice(new File("000"));
System.out.println(sd.getPatientName());
```


**Images not yet ready for display**


This is my first public Java package, please excuse any bad stylistic choices (and let me know how I can improve) as I haven't learnt Java from an educational institution but rather from trial and error in 
<a href="https://processing.org">Processing</a>.

<a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License</a>.
