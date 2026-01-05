## 📦 패키지 구조
```
deed
├── adapter
│   ├── inbound.web
│   │   ├── dto
│   │   │   ├── DeedRequest
│   │   │   └── DeedResponse
│   │   └── DeedInboundWebAdapter
│   └── outbound.persistence
│       ├── jpa
│       │   ├── AnalysisJobEntity
│       │   └── AnalysisJobRepository
│       ├── AnalysisJobentityMapper
│       └── AnalysisJobPersistenceAdapter
├── application
│   ├── port
│   │   ├── inbound
│   │   │   └── DeedUseCase
│   │   └── outbound
│   │       └── AnalysisJobPersistencePort
│   └── usecase
│       └── DeedUseCaseImpl
└── model
    └── AnalysisJob
```