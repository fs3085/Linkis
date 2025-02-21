/*
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.webank.wedatasphere.linkis.manager.engineplugin.manager.classloader.EngineConnPluginClassLoader;
import com.webank.wedatasphere.linkis.manager.engineplugin.manager.utils.EngineConnPluginUtils;

import java.io.File;
import java.net.URL;


public class EngineConnPluginLoaderTest {
    public static void main(String[] args) throws Exception {
        EngineConnPluginClassLoader enginePluginClassLoader = new EngineConnPluginClassLoader(new URL[]{
                new File("").toURI().toURL()
        }, Thread.currentThread().getContextClassLoader());
        String engineConnPluginClass = EngineConnPluginUtils.getEngineConnPluginClass(enginePluginClassLoader);
        Class<?> clazz = Class.forName(engineConnPluginClass, true, enginePluginClassLoader);
        clazz.newInstance();
    }
}
