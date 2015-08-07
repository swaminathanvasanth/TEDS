/*
 *
 * $RCSfile: callLDAPAsyncTask.java $
 *
 * Copyright (c) 2015, RBCCPS, IISc Bangalore.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *    -	Redistributions of source code must retain the above
 *      copyright notice, this list of conditions and the following
 *      disclaimer.
 *    -	Redistributions in binary form must reproduce the above
 *      copyright notice, this list of conditions and the following
 *      disclaimer in the documentation and/or other materials provided
 *      with the distribution.
 *    -	Neither the name of RBCCPS, IISc Bangalore nor the names
 *      of its contributors may be used to endorse or promote products
 *      derived from this software without specific prior written
 *      permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */


package rbccps.iot.ncap.SNaaS.CP.TEDS.LDAP;

import android.os.AsyncTask;
import android.util.Log;

import org.forgerock.opendj.ldap.Connection;
import org.forgerock.opendj.ldap.LDAPConnectionFactory;
import org.forgerock.opendj.ldap.SearchScope;
import org.forgerock.opendj.ldap.responses.SearchResultEntry;
import org.forgerock.opendj.ldap.responses.SearchResultReference;
import org.forgerock.opendj.ldif.ConnectionEntryReader;
import org.forgerock.opendj.ldif.LDIFEntryWriter;

import rbccps.iot.ncap.SNaaS.CP.TEDS.Decoder.RuntimeBinaryArraySplitter;
import rbccps.iot.ncap.SNaaS.CP.TEDS.LocalCache.TEDSDataStore;
import rbccps.iot.ncap.StateMachine.ApplicationContext;

public class callLDAPAsyncTask extends AsyncTask<Void,Void,Void> {

    public static boolean queried = false;
    public static String binaryTEDS;
    private TEDSDataStore datastore;
    public static String uuid = "000000144F01000061DB";

    @Override
    protected Void doInBackground(Void... params) {


        String ldap_arg = "tedsuuid=".concat(uuid);

        System.out.println(ldap_arg);
        Log.e("ldap_arg", "" + ldap_arg);

        final LDIFEntryWriter writer = new LDIFEntryWriter(System.out);
        try {
            final LDAPConnectionFactory factory =
                    new LDAPConnectionFactory("10.156.14.50", 389);

            Connection connection = (Connection) factory.getConnection();
            final ConnectionEntryReader reader =
                    ((org.forgerock.opendj.ldap.Connection) connection).search("dc=rbccps,dc=org",
                            SearchScope.WHOLE_SUBTREE, ldap_arg, "binaryteds");
            while (reader.hasNext()) {
                if (reader.isEntry()) {
                    // Got an entry.
                    final SearchResultEntry entry = reader.readEntry();
                    binaryTEDS = entry.getAttribute("binaryteds").firstValueAsString();

                    // Push to SQL (Local Cache)
                   // TEDSDataStore datastore = new TEDSDataStore(ApplicationContext.context);

                    System.out.println("Binary TEDS found is: " + binaryTEDS);
                    queried = true;
                    RuntimeBinaryArraySplitter.split(binaryTEDS);
                    // BluetoothLE_Scan_Result_Processor.process(Address, "RBCCPS_3_1", rssi, time_stamp);

                } else {
                    // Got a continuation reference.
                    final SearchResultReference ref = reader.readReference();
                    writer.writeComment("Search result reference: "
                            + ref.getURIs().toString());
                }
            }
            writer.flush();
            datastore = new TEDSDataStore(ApplicationContext.context);

            // CPDPSwitch.addtoList(uuid);

        } catch (final Exception e) {
            // Handle exceptions...
            System.err.println(e.getMessage());
        } return null;
    }
}
