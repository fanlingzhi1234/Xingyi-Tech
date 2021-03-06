## SDK开发

转自：https://www.jianshu.com/p/28becca53726





**SDK开发和app开发的区别：**
 从用户角度来讲：
 app开发主要面向的是普通的用户需求，SDK开发面向的是开发人员。
 从技术角度来讲：
 app开发更多的只是UI层面、基于数据流的技术实现；而SDK开发可能要涉及更多的复杂的需求、更多底层的技术实现。

# 相关概念

**什么是SDK**
 SDK，Software Development Kit,软件开发工具包，通常是为辅助开发某类软件而编写的特定软件包，框架集合等，SDK一般包含相关文档，范例和工具。

SDK可以分为系统SDK和应用SDK，所谓的系统SDK是为特定的软件包，软件框架，硬件平台，操作系统等所使用的开发工具集合。而应用SDK则是基于系统SDK开发的独立于具体业务而具有特定功能的集合。
 比如在进行Android应用开发时，我们使用Google提供的系统SDK（Android SDK）,而我们经常使用的友盟SDK，极光SDK则是基于系统SDK开发的。

**什么是Library**
 Library即库，通常是一组或者几组类的集合，通常是应用中某些功能的具体实现或者对系统已有功能的增强或补充。对Android开发者而言，最常见的是Support Library,另外就是我们经常使用各种网络请求库（OkHttp,Vooley）,数据库操作，图片加载库（Glide,ImageLoader）等。

**什么是Framework**
 Framework,即框架，通常是系统或者应用的骨架，它表现为一组抽象的构建及构件实例间交互的方法。因此，可以认为Framework规定了应用的体系结构，阐明了整体设计，写作构件之间的依赖关系以及控件流程。
 注意：这里的Framework并不完全等同于Android Framework框架，可以认为Android Framework中体现了Framework的思想，并进行了实现。

**什么是API**
 API是Application Programming Interface,又称为应用编程接口，是软件系统不同组成部分衔接的约定。简单来说，API就是我们常见和编写的方法或函数。

**小结：**

> SDK,Library,Framework,API四者之间的关联（SDK的主要构成）
>  SDK主要包含Framework,API及Library的三部分。Framework定义了SDK整体的可重用设计，规定了SDK各功能模块的职责以及依赖关系。其中功能模块体现为Library。模块之间的内部通信及SDK外部通信（SDK对外提供服务的接口），则通过API进行。
>  另外完整的SDK还应该包含大量的示例和其他工具，比如在Android SDK的tools目录下提供了大量的辅助开发工具。
>  对我们而言，大部分情况下是为某种具体的业务需求开发对应的SDK，以便作为第三方提供给其他需求方使用，比如百度推送的SDK主要实现消息推送功能，需求方只需要集成百度推送的SDK便可以使自己应用具备推送功能。





## 第一节 Library、API、SDK 以及 Framework

目前来说，并没有统一的、官方的文档定义 `Android` 应用开发中常见的 `Library`、`API`、`SDK` 以及 `Framework` 这些概念。我们基于其字面意思以及日常使用习惯做如下的解释：

-  **Library** `Library` 是一组或几组类的集合，可以直接调用，使得开发更高效。`Library`往往是对系统已有功能的增强或是对应用程序架构中功能模块的具体实现。比如 `Android SDK` 中提供的[`Support Library`](https://link.jianshu.com?t=http://developer.android.com/intl/zh-cn/tools/support-library/index.html)，著名的开源项目如[`Volley`](https://link.jianshu.com?t=https://android.googlesource.com/platform/frameworks/volley) 、[`Picasso`](https://link.jianshu.com?t=https://github.com/square/picasso)、[`Android-Universal-Image-Loader`](https://link.jianshu.com?t=https://github.com/nostra13/Android-Universal-Image-Loader)。
-  **API** 即 `Application Programming Interface`，也就是软件系统不同组成部分（模块）衔接的约定。由于软件的规模日趋庞大，常常需要把复杂的系统划分成小的组成部分，`API` 的设计就显示尤为更要。良好的程序设计实践中，`API` 的设计首先要使软件系统的职责得到合理的划分，降低系统各组成部分的相互依赖，并提升组成单元的内聚性，从而提高系统的可维护性以及扩展性。
-  **SDK** 即 `Software Development Kit`，广泛意义上的 `SDK` 一般都是为特定的软件包、软件框架、硬件平台、操作系统等建立应用程序时所使用的开发工具的集合（`系统 SDK`）。而狭义上的 `SDK`（`应用 SDK`） 则是基于`系统 SDK` 进行开发的新的、独立于具体业务且完成特定功能的一组工具的集合。例如`友盟统计 SDK`、`极光推送 SDK`、`多盟广告 SDK`。
-  **Framework** `Framework` 是整个或部分系统的可重用设计，表现为一组抽象构件及构件实例间交互的方法。另一种定义认为，`Framework` 是被应用开发者定制的应用骨架。可以说，一个 `Framework` 是一个可复用的设计构件，它规定了应用的体系结构，阐明了整个设计、协作构件之间的依赖关系、责任分配和控制流程，表现为一组抽象类（或接口）以及其实例之间的协作方法，它为构件复用提供了上下文（`Context`）关系。

一般来说，`SDK` 是 `Framework`、`API` 以及 `Library` 的集合。`Framework` 定义了 `SDK` 整体的可重用设计，规定了 `SDK` 各功能模块的职责以及依赖关系。`SDK` 中功能模块的具体实现则是 `Library` 的主要职责。各模块之间的通信以及 `SDK` 所能提供的服务则通过 `API` 体现出来。

通常情况下，`SDK` 在应用程序中是作为特定功能提供者的角色出现的。例如推送功能的 `SDK`、统计功能的 `SDK`、广告功能的 `SDK`、性能监测功能的 `SDK` 以及分享功能的 `SDK` 等等。

## 第二节 SDK 设计

前文中说到 `SDK` 是作为应用程序中特定功能的提供者而存在的。通常情况下，`SDK` 是作为第三方服务而被引入到应用程序中的，`SDK` 的品质能够影响到应用程序的品质。

### 易用性

肥肥认为，好的 `SDK` 产品应该是易于使用的。我们想要创造一种简单的模式，让 `SDK` 的使用者在他们的应用中方便的使用 `SDK`，那么这种模式应该是不需要侵入太多的代码或者不需要繁琐的集成工作的。

如果一个 `API` 的调用方式正好是开发者所预期的方式，那么我们认为该 `API` 的调用方式是易用的表现。多数情况下，`API` 的品质直接决定了 `SDK` 的品质。`SDK` 的易用性体现在 `API` 的易用性上，那么，好的 `API` 设计也就显得尤为重要。

通常情况下，`API 难以被误用` 也是易用性的一种，这样可以有效地避免一些错误的发生。比如，对参数的校验、对边界的严格检查以及详细的说明文档，都将使得开发者在使用 `SDK` 的时候，能够有效地避免一些错误的。

### 稳定性

从 `SDK 使用者`角度来说，在 `SDK` 使用过程中，我们假设 `SDK` 本身是可靠的，不会影响到程序本身的稳定性。那么，从 `SDK 设计者`的角度来说，`SDK` 作为第三方服务，其稳定性是尤为重要的。这种稳定性体现在如下四个方面：

- 对外提供服务的 `API` 的稳定性，`SDK` 对外的 `API` 一旦确定，其变更的成本（即使仅仅在某个 `API` 上增加或减少一个参数）是非常高昂的。
- 业务的稳定性，业务的稳定性是对 `API` 稳定性的补充。底层的 `SDK 业务`通常决定了上层 `API` 的形态。
-  `SDK 运行时`的稳定性，作为第三方服务提供者，自身的稳定性非常重要。
-  `SDK 版本迭代`的稳定性，相对于应用程序的 `Release 版本`迭代速度，`SDK` 的 `Release 版本`的迭代速度是相对缓慢的。频繁的 `SDK` 升级会给应用程序开发者带来额外的升级成本，并给应用程序开发者留下 `SDK` 不稳定的印象。

### 灵活性

通常情况下，`SDK` 开发者并不能像应用开发者那样拥有更多的选择权。我们不能选择设备，系统版本，甚至是目标客户。相应的，我们需要最大化支持设备，提供高度灵活的 `API` 设计，以满足不同客户群的需要。

可以让开发者选择不同的依赖管理器或者构建工具来集成 `SDK`，是灵活性的一大体现。面对形形色色的应用程序开发团队，我们也要尽可能的去迎合这些团队所使用的开发环境，提供一些主要的开发工具插件的支持，包括 `Gradle` 、`Maven`以及 `Ant`等。

灵活性设计的关键是了解你的 `SDK 用户`的需求，然后做出需要支持的最低系统版本的决定。我们很希望我们的 `SDK` 能够支持尽可能多的系统设备，对于这一点，降低支持最低操作系统版本是很有必要尽力去做的。

但从另一方面来看，兼容低版本也是要付出代价的。并没有什么直接的法则能够告诉我们如何才能在繁琐度和更好的兼容性上权衡。支持旧的操作系统版本，通常意味着不能使用操作系统的新特性，同时还要面对一些旧版本存在的问题。除此之外，我们还要花费更多精力去测试代码的正确性以及兼容性。

### 最小资源开销

相对于 `PC` 来说，移动设备的硬件资源显得尤为珍贵。`SDK` 应尽可能的降低如下几种系统资源开销：

- 内存以及 `CPU`，`SDK` 在尽可能降低内存占用的情况下，也应该尽量保证内存占用的稳定性（避免`内存抖动`）。
- 电量，对于 `SDK` 的电量开销很难有统一的标准来衡量。手机的各个硬件模块的耗电量是不一样的，有些模块非常的耗电，而有的模块耗电量则相对显得很小。但是，尽可能的为用户省电是值得推荐的做法。
- 网络流量，相较于内存及 `CPU` 的开销来说，应用程序的使用者对于电量以及网络流量消耗更为敏感。
- 存储资源，对于应用程序来讲，`Android` 设备的存储路径大致能够分为两类：

1. 应用程序目录下存储（`/data/data/package_name/`和`/storage/sdcard0/Android/data/package_name/`目录）
2.  `SD` 卡下非应用程序目录存储

对于 `SDK` 来说，如果没有完全的必要性（比如使用 `SharedPreference`），选择 `SD` 卡目录存储数据是一种不错的选择。

这样做的好处在于，一方面可以减少`/data/data/package_name/`和`/storage/sdcard0/Android/data/package_name/`目录的存储压力，另一方面则方便多个应用程序间共享文件。

当然，无论使用何种存储目录，为 `SDK` 创建独立的文件夹是非常有必要的（比如 `SDK` 使用`/data/data/package_name/sdk_cache`目录），这也是为了方便将 `SDK` 文件与应用程序文件区分开来。

相较于目前动辄16G 起步的存储空间来说，应用程序的使用者对于电量和网络流量的消耗显得尤为敏感。究其原因，可能是电量和网络流量是应用程序使用者能够直接接触到的一些指标。在即便是网络流量白菜价的年达，也会有很大一部分用户因为抠门亦或是运营商等原因，仍旧使用着每月几十兆流量的套餐。而对于电量的敏感，可能就是因为现代人都有的 `低电量恐惧症` 这样时髦的毛病了。

内存以及 `CPU` 的过度使用，一方面带来了过度的电量开销，另一方面则可能造成应用程序卡顿甚至 `ANR` 等问题。

这些问题都能够或直接或间接的影响到应用程序使用者对一款应用程序的评价。

#### 主线程

众所周知，`Android` 系统中`主线程`又被称为 `UI 线程`，理想情况下，`主线程`只负责向 UI 组件分发事件（`触屏事件`、`渲染事件`等）。

系统并不会为每个组件创建单独线程，在同一个进程中的 `UI 组件`都会在 `UI 线程`中实例化，系统对每一个组件的调用都从 `UI 线程`分发出去。那么由此引发的问题就是，响应系统回调的方法（`组件生命周期`、`触屏事件`等）都是在 `UI 线程`中执行的。

如果所有的工作都是在 `UI 线程`中执行，特别是做一些耗时的操作（`Http 请求`、数据库查询以及文件读写等），都会阻塞 `UI 线程`，导致事件的分发停止。从用户的角度来看，是应用程序卡顿甚至卡住了。更为糟糕的情况是，如果 `UI 线程`阻塞的时间过长（`UI 线程`中大约5秒，`BroadcastReceiver` 中大概10秒），系统就会弹出 `ANR`（`Application Not Response`）对话框。

从另一个方面来说，`Android` 的 `UI 组件`并非是`线程安全`的，也就意味着不能从`非 UI 线程`操作 `UI 组件`。所以，`SDK` 的线程模型有四条重要的设计原则：

1. 不能阻塞 `UI 线程`；
2. 不能在 `UI 线程`之外操作 `UI 组件`（`SurfaceView` 不受该原则限制）。
3. 除非 `SDK` 必须，否则不能使用应用程序主线程。如果必须使用主线程，那么不能长时间占用。
4.  `SDK` 应该有一个专门的线程来处理 `SDK` 的相关业务。

### 最小权限原则

`Android 应用程序权限机制`限制应用程序访问特定的资源，如照相机、网络、存储系统以及查询用户数据以及以及某些 `API` 的调用。

一般来说，系统会在应用程序安装过程中提醒用户该应用程序所申请的权限，如果所申请的权限过高（`Root 权限`）则会在应用程序申请该权限时弹出窗口进行通知。

而自 `Android 6.0` 开始则使用了全新的权限控制系统，除了以上权限控制的机制之外，还会在应用程序访问特定系统功能时（比如使用蓝牙模块），也会通过弹出窗口的形式的进行通知。

相对应的，在 `SDK` 开发过程中，我们应该为 `Android 6.0` 及以上版本单独做权限方面的适配工作。

那么，作为第三方服务的 `SDK` 一定要遵循的一个原则就是：`最小权限原则`。`最小权限原则`指的是，`SDK` 尽可能不要申请非必要的权限，尽可能的不要给使用 `SDK` 的应用程序带来额外的权限申请。

举例来说，如果 `SDK` 中并没有使用到拨打电话的功能，但是却要求应用程序开发者在`AndroidManifest.xml`文件中声明 `android.permission.CALL_PHONE`权限，那么就是违反了`最小权限原则`。

`违反最小权限原则`并不会对 `SDK` 本身的业务带来任何影响，但是这会使得应用程序向系统申请不必要的权限而造成的额外的权限开支。由此带来的后果是用户对于应用程序的不良印象。

### 严格的生命周期把控

`SDK` 作为服务的提供者，定义清晰且严谨的生命周期模型显得尤为重要。一种简单的做法就是 `SDK` 的生命周期托管给当前 `Activity` 的生命周期管理。由此带来的好处就是，`SDK` 可以在恰当的时机做恰当的事情。比如我们可以在 `onCreate()` 的生命周期中完成一些初始化的工作，而在`onDestroy()`的生命周期中完成对象的销毁工作以及在应用程序的`onPause()`状态暂定一些后台的操作以节省资源。

## 第三节 API 设计

本文中，我们假定 `API` 设计只涉及如下两方面：

-  `SDK` 对外提供服务的 `API 设计`，后文简称 `SDK API` 或者`公共 API`；
-  `SDK` 内部各模块间的 `API 设计`，后文简称`模块 API`。

之所以将这两方面拆分出来，是因为肥肥觉得这是两种不同的 `API` 设计理念。首先是面向的用户群体不同，`SDK API` 面向的是 `SDK 使用者`，也就是商业用户，而`模块 API` 则是面向 `SDK` 团队中的其他开发人员。其次，`SDK API` 是由具体的使用场景而决定的，而`模块 API` 则是由具体的功能而决定的。

从公司的角度来说，`API` 的通用商业价值是可以进行评估的。从数据的角度来看，`API` 应该算是公司资产的一种，因为设计优良的 `API` 实现了数据的可访问性、准确性、可应用性以及安全性。每一个`公共 API` 都在某些程度上提供了特定数据的可访问性，而设计优良的`公共 API` 则很大程度上保证了数据的准确性以及安全性。对于每一个开发人员来说，只要参与到编程的过程中，那么你就是一名 `API` 的设计者——因为好的代码即是模块，每一个模块就是一个 `API`（虽然这并不适用于 `SDK API` 的开发）。

与 `SDK` 内部模块 `API` 的设计相比，`SDK API` 的设计难度要更大一些。 我们下文中的讨论围绕 `SDK API` 的设计展开，当然其也适用于`模块 API` 的设计。

好的 `API` 设计来自于迭代过程。

在开始设计你的 `API` 之前，你应该先了解设计这个 `API` 的目的，这也就意味着我们要设计出一种接口，让它的使用方式符合 `API` 本身的设计目的。作为 `SDK 开发者`，我们对 `API 设计`所做的任何一个决策都会影响到 `SDK` 产品的质量。在我们能够做出一个正确的决策之前，很可能会先做出一个错误的决策，并从中吸取教训。实际上，在经历了多次的错误决策之后才可能接近正确的决策。

这正是 `API 设计`中迭代的意义。在实际的操作过程中，我们所面临的一项挑战在于，在某个 `API` 发布之后，再进行变更的成本变得非常高昂，并伴随着非常大的风险。

我们力求在 `API` 变更的成本变得高昂之前，就消除易用性与设计方面的问题。这需要强有力的对于产品需求的把控、全面的测试以及深厚的 `API 设计`功力来保证。

设计良好的 `API` 应该具备如下几个特点：

-  **风格统一：**有较为统一的命名风格；
-  **易于学习：**有完善的使用文档以及示例代码，尽可能降低使用者的学习成本；
-  **易于使用：**有详尽的注释以及易于理解且表意直观的命名；
-  **接口安全：**有详细的错误提示，并对非法参数进行校验；
- **功能单一，但是足够强大；**

### 单一职责原则

单一职责原则说的是在类或方法的设计中，应该保证有且仅有一个引起类或方法变化的原因。通俗来说就是一个类或方法只负责一项职责。如果有两个比较接近的功能，但是使用一个接口实现有点繁琐，那么就应该使用两个接口。不要为了减少接口的数量而生硬的把两个接口合并为一个。

### 参数尽可能少

接口调用中应尽可能少的要求调用中传递参数。如果 `SDK` 能自己获取的参数就不需要让开发者传递。

在同一个接口中使用大量的相同类型的参数也是不推荐的。如果无法避免，建议将参数封装成对象。

### 参数合法性校验

参数合法性校验应该是接口要做的第一件事情。所有的参数必须校验其合法性，并视具体业务对不合法参数进行处理。一般情况下，除了必要参数，对于其他参数可以使用默认值或者区间值（超过最大、最小值使用最大、最小值）的方式来确保业务的正常流程。如果必要参数不合法，可以考虑使用抛出运行时异常的方式通知开发者。

### 优美的降解

开发者经常容易不耐烦，所以对于一些错误或异常，应该尽可能早的抛出。比如一些能够在编译期间就能抛出，终归好于在运行期间抛出。也就是说，`SDK 开发者`应该尽可能早的把一些可以预期的异常抛出，以便让开发者尽快处理这些异常。

### 实现不要影响 API

正式发布的 `SDK` 的接口应该是稳定的，这其中包括其参数类型、返回值类型、异常类型。

我们假设正式发布的 `SDK` 中的任何一个接口，都有机会被调用。那么，这样也就要求我们在后续的版本迭代中保证接口的参数类型、返回值类型以及异常类型是统一的。

如果需要变更接口功能，建议增加新的接口而不是改变现有接口。

## 第四节 版本管理

在 `SDK` 的升级、维护策略中，版本管理是一个非常重要的组成部分：

- 应用程序开发者需要了解他们所使用的 `SDK 版本`的特定信息，以及已使用的 `SDK` 的升级版本的可用情况；
-  `SDK 开发者`需要使用版本号来定位 `SDK` 使用过程中所存在的问题，并建立 `SDK` 升级的依据。

版本号的命名及管理并没有统一的标准，不同的团队往往使用不同的命名风格。

但是无论使用哪种版本命名风格，给出详尽的版本变更记录是一种不错的选择。

### SDK 版本迭代状态

按照软件版本的发布阶段来看，一款成熟稳定的 `SDK` 产品的版本迭代往往会经历如下阶段：

-  **alpha 版：**该版本表示该 `SDK 产品`在此阶段主要是以实现功能为主，通常只在开发团队内部交流使用。一般来说，该版本的 `SDK 产品`存在的 `Bug` 较多，需要经历多个 `alpha 版本`的迭代才能进入 `beta 版`。
-  **beta 版：**该版本相对于 `alpha 版`已经有了很大的改进，修复了严重的 `Bug`，但是还存在一些已知或是未知的 `Bug`，通常情况下只在开发团队以及测试团队之间交流使用，需要经历多个 `beta 版本`的迭代才能进入 `rc 版`。
-  **release candidate 版（rc 版）：**该版本的 `SDK` 趋于成熟，基本上不会出现导致错误的 `Bug`，原则上不再增加新的功能，与正式发布的正式版没有太大的差异。通常情况下该版本用于进行小规模灰度测试，原则上不会提供给应用程序开发者使用。
-  **release 版：**该版本意味着 **最终发布**，在经历了前面几个版本的迭代之后产生的最终版本，也就是最终交付到应用程序开发者使用的版本。

### SDK 版本号命名

一个比较合理的版本号命名规范由如下四部分组成：

**V1_0_2_201511171733_beta**

1. 主版本号（1）；
2. 子版本号（0）；
3. 阶段版本号（2）；
4. 迭代版本号（201511171733_beta）。

### SDK 版本号修改原则

-  **主版本号：**当功能模块有较大的变动，比如增加多个模块或者 `SDK` 整体架构发生变化时，由需求决定是否修改。
-  **子版本号：**当功能有一定的增加或变化时，由项目决定是否修改。
-  **阶段版本号：**当修复 `Bug` 以及小规模调整时，需要经常发布修订版，此时可由项目经理决定是否修改。
-  **迭代版本号：**用于记录该版本的 `SDK` 发布时的时间以及当前的迭代状态。原则上，当项目处于 `alpha`、`beta`以及 `rc 版`时，该版本号需要体现每一次的修改时间以及状态。当项目处于 `release 版`时，该版本号用于记录该版本的发版时间。

### API 版本管理

`API` 的版本受到 `SDK 版本迭代状态`的约束，但是不受 `SDK 版本号`修改原则的限制。

只有处于 `release`（或 `rc` ） 状态的  `API` 才能是对外提供服务的，否则该 `API` 应该是对应用程序开发人员不可见的。换句话说就是，坚决不发布处于 `alpha` 和 `beta` 状态的 `API`。

`API` 一旦对外发布，其内部实现以及方法签名原则上处于不可变更状态：

- 如果需要修改 `API` 的内部实现，在保证方法签名不变的情况下，`API` 必须通过测试用例的边界及功能测试，并尽可能的给出原 `API` 实现的备份——使用`oldMethodName`前缀标识原 `API`；
- 如果需要变更方法签名，比如增加、删除参数或是改变返回值类型，那么在保证原 `API` 不变的情况下，使用方法重载实现新的 `API`。
- 如果需要废弃某些 `API`，应在 `SDK release 版本`迭代的前 N 个版本使用 `@deprecated`  标识需要废弃的 `API`，并给出该 `API` 的替代方案以及具体的 `API` 移除时间（或是 `SDK` 版本）。

### Http 接口版本管理

`SDK` 一旦发布，你将无法强制要求应用程序开发者跟随你的 `SDK 版本`迭代而更新他们的代码。在一定周期内，将会有多个 `SDK 版本`在提供服务，除了建议开发者升级 `SDK` 之外，服务端将不得不为多个 `SDK` 版本提供支持。

从另一方面来看，随着需求的变更，`API` 会相应的增加或声明废弃。与之相对应的，`Http 接口`往往也会发生相应的变化。

### 文档以及 Demo 版本管理

一种比较合理的做法是，文档以及 `SDK` 对应的 `Demo` 受 `SDK 版本`的管理。更为简便的做法就是文档以及 `Demo` 采用 `SDK 版本号`进行统一管理。普遍的做法是，即便是 `SDK` 接口的轻微改变，也要及时的体现在对应的文档上，并更新对应的 `Demo`。在 `SDK` 上线初期，其迭代频率相对较高，那么就会出现多个版本 `SDK` 共存的情况。合理的文档、`SDK` 以及 `Demo` 间的版本关系，也就显得尤为重要。

## 第五节 总结

`SDK` 开发是一个很大的范畴，相较于应用程序的开发，有相似之处，也有不同之处。从面相的客户全体来说，应用程序开发者面向的是普通用户，而 `SDK` 开发者则面向应用程序开发人员。从服务的角度来说，应用程序开发人员在设计应用的时候，往往要考虑性能、兼容性、用户体验、渠道以及版本迭代。而 `SDK` 开发人员不仅要全面考虑上面这些因素，还要近乎于苛刻的将性能、兼容性提升到极致。对于某项需求的验证，应用程序开发人员会选择在部分灰度版本中进行验证，而 `SDK` 开发人员则没有这样的幸运，只能依赖对业务的高度抽象进行验证。当然，目前普遍的做法是基于自己的 `SDK` 开发相应的应用程序，一方面能够进行一些需求的验证，另一方面，自己成为自己的客户，也未尝不是一件坏事。

肥肥不才，文章前后修改数次，历经四月，终于写完《Android SDK 开发》的第一部分。这期间肥肥仔细拜读了 **参考文献** 中各位前辈的文章，受益颇多。肥肥在文章的有些章节内容中，直接参考了一些前辈的观点，甚至存在一些直接复制的行为。在此向各位前辈致以最高的敬意，并为肥肥的剽窃行为作出道歉。

剩余的内容将会围绕 `SDK` 的测试、安全性、业务配置以及数据运营展开讨论。

## 版本记录

- 2016年09月20日 初稿撰写，发布。

## 参考文献

[Difference between framework vs Library vs IDE vs API vs SDK vs Toolkits?](https://link.jianshu.com?t=http://stackoverflow.com/questions/8772746/difference-between-framework-vs-library-vs-ide-vs-api-vs-sdk-vs-toolkits)

[Framework](https://link.jianshu.com?t=http://www.baike.com/wiki/Framework)

[http://developer.android.com/intl/zh-cn/training/articles/memory.html](https://link.jianshu.com?t=http://developer.android.com/intl/zh-cn/training/articles/memory.html)

[http://blog.csdn.net/zhao_3546/article/details/10713471](https://link.jianshu.com?t=http://blog.csdn.net/zhao_3546/article/details/10713471)

[http://www.codeceo.com/article/android-memory-manage.html](https://link.jianshu.com?t=http://www.codeceo.com/article/android-memory-manage.html)

[http://www.csdn.net/article/2015-09-18/2825737/1](https://link.jianshu.com?t=http://www.csdn.net/article/2015-09-18/2825737/1)

[http://liaohuqiu.net/cn/posts/storage-in-android/](https://link.jianshu.com?t=http://liaohuqiu.net/cn/posts/storage-in-android/)

[https://www.ibm.com/developerworks/cn/opensource/os-cn-android-sec/](https://link.jianshu.com?t=https://www.ibm.com/developerworks/cn/opensource/os-cn-android-sec/)

[http://johnhax.net/2011/js_api_design/](https://link.jianshu.com?t=http://johnhax.net/2011/js_api_design/)

[http://www.csdn.net/article/2012-08-14/2808557](https://link.jianshu.com?t=http://www.csdn.net/article/2012-08-14/2808557)

[http://itindex.net/detail/35806-java-api-](https://link.jianshu.com?t=http://itindex.net/detail/35806-java-api-)设计

[http://itindex.net/detail/44696-](https://link.jianshu.com?t=http://itindex.net/detail/44696-)设计-api

[http://www.infoq.com/cn/articles/web-apis-business-perspective](https://link.jianshu.com?t=http://www.infoq.com/cn/articles/web-apis-business-perspective)

[http://www.infoq.com/cn/articles/doodles-to-delivery](https://link.jianshu.com?t=http://www.infoq.com/cn/articles/doodles-to-delivery)

[http://www.csdn.net/article/2014-02-18/2818441-How-to-design-a-good-API](https://link.jianshu.com?t=http://www.csdn.net/article/2014-02-18/2818441-How-to-design-a-good-API)

[https://realm.io/cn/news/oredev-ty-smith-building-android-sdks-fabric/](https://link.jianshu.com?t=https://realm.io/cn/news/oredev-ty-smith-building-android-sdks-fabric/)

[http://blog.bihe0832.com/SDK_desigin_api.html](https://link.jianshu.com?t=http://blog.bihe0832.com/SDK_desigin_api.html)

[http://blog.bihe0832.com/SDK_experience_test.html](https://link.jianshu.com?t=http://blog.bihe0832.com/SDK_experience_test.html)

