#include <jni.h>
#include <windows.h>
#include <stdio.h>
#include "JEventViewer.h"
#define BUFFER_SIZE 1024*64

JNIEXPORT jobject JNICALL Java_JEventViewer_displayEntries (JNIEnv *env, jobject obj, jobject vector)
{



		const char *str = "hi";
		HANDLE h;
		EVENTLOGRECORD *pevlr;
		BYTE bBuffer[BUFFER_SIZE];
		DWORD dwRead, dwNeeded, dwThisRecord;

		//preparing java vector
		jclass clsvec = (*env)->FindClass(env,"java/util/Vector");
		jmethodID mid = (*env)->GetMethodID(env, clsvec, "addElement", "(Ljava/lang/Object;)V");
		if (mid == NULL) printf("method ID not valid\n\n");
		jmethodID jsize = (*env)->GetMethodID(env, clsvec, "size", "()I");
		if (jsize == NULL) printf("method ID not valid\n\n");
		jmethodID elemAt = (*env)->GetMethodID(env, clsvec, "elementAt", "(I)Ljava/lang/Object;");
		if (elemAt == NULL) printf("method ID not valid\n\n");

		// checking if vector is empty
		printf("---> Vector size before adding elements %i\n", (*env)->CallIntMethod(env,vector, jsize));




		// Open the Application event log.
		h = OpenEventLog( NULL, // use local computer
		TEXT("Application")); // source name
		if (h == NULL)
		{
		printf("Could not open the Application event log.");
		return;
		}

		pevlr = (EVENTLOGRECORD *) &bBuffer;

		// Get the record number of the oldest event log record.

		GetOldestEventLogRecord(h, &dwThisRecord);

		// Opening the event log positions the file pointer for this
		// handle at the beginning of the log. Read the event log records
		// sequentially until the last record has been read.

		while (ReadEventLog(h, // event log handle
		EVENTLOG_FORWARDS_READ | // reads forward
		EVENTLOG_SEQUENTIAL_READ, // sequential read
		0, // ignored for sequential reads
		pevlr, // pointer to buffer
		BUFFER_SIZE, // size of buffer
		&dwRead, // number of bytes read
		&dwNeeded)) // bytes in next record
		{
		while (dwRead > 0)
		{
		// Print the record number, event identifier, type,
		// and source name.

		// preparing element
		jclass longClass = (*env)->FindClass(env,"java/lang/Long");
		jmethodID mlongid = (*env)->GetMethodID(env, longClass, "<init>","(J)V");
		jobject longElement1 = (*env)->NewObject(env, longClass, mlongid, (jlong)pevlr->EventID );



		//adding new elements


			(*env)->CallVoidMethod(env, vector, mid, longElement1 );

		long type=0;
		char* buf;






		switch(pevlr->EventType)
		{
		case EVENTLOG_ERROR_TYPE:
		type=1;
		buf="Error";
		break;
		case EVENTLOG_WARNING_TYPE:
		type=2;
		buf="warning";
		break;
		case EVENTLOG_INFORMATION_TYPE:
		type=3;
		buf="info";
		break;
		case EVENTLOG_AUDIT_SUCCESS:
		type=4;
		buf="A success";

		break;
		case EVENTLOG_AUDIT_FAILURE:
		type=5;
		buf="A failure";

		break;
		default:
		type=6;
		buf="Unknown";
		break;
	}
//		 longElement1 = (*env)->NewObject(env, longClass, mlongid, (jlong)type );



	//	 jstring jst = (*env)->NewStringUTF(env, str);


		(*env)->CallVoidMethod(env, vector, mid, (*env)->NewStringUTF(env, buf) );
		(*env)->CallVoidMethod(env, vector, mid, (*env)->NewStringUTF(env, (LPSTR) ((LPBYTE) pevlr + sizeof(EVENTLOGRECORD))) );
		buf=(BYTE*)pevlr+pevlr->DataOffset;
		(*env)->CallVoidMethod(env, vector, mid, (*env)->NewStringUTF(env, buf) );

		//printf("Event source: %s\n",
		//(LPSTR) ((LPBYTE) pevlr + sizeof(EVENTLOGRECORD)));

				dwRead -= pevlr->Length;

				pevlr = (EVENTLOGRECORD *)
				((LPBYTE) pevlr + pevlr->Length);
				}

				pevlr = (EVENTLOGRECORD *) &bBuffer;
				}

				CloseEventLog(h);

				return vector;
		}

